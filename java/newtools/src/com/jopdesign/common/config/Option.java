/*
 * This file is part of JOP, the Java Optimized Processor
 *   see <http://www.jopdesign.com/>
 *
 * Copyright (C) 2008, Benedikt Huber (benedikt.huber@gmail.com)
 * Copyright (C) 2010, Stefan Hepp (stefan@stefant.org).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jopdesign.common.config;


/**
 * Typed options for improved command line interface
 *
 * @author Benedikt Huber <benedikt.huber@gmail.com>
 * @author Stefan Hepp <stefan@stefant.org>
 *
 * @param <T> java type of the option
 */
public abstract class Option<T> {

    public static final char SHORT_NONE = ' ';

	protected String  key;
    protected char    shortKey = SHORT_NONE;
    protected String  description;
    protected boolean optional;

    /**
     * If an option with this flag is set, OptionChecker is not executed (for flags like 'help' or 'version').
     */
    protected boolean skipChecks = false;

    protected Class<T> valClass;
    protected T defaultValue = null;

    @SuppressWarnings("unchecked")
    public Option(String key, String descr, T defaultVal) {
        // Class<T> cast is always safe, shortcoming of Java Generics
        this(key, (Class<T>) defaultVal.getClass(), descr, true);
        this.defaultValue = defaultVal;
    }

    protected Option(String key, Class<T> optClass, String descr, boolean optional) {
        this.key = key;
        this.valClass = optClass;
        this.description = descr;
        this.optional = optional;
    }

    /**
     * Get the default value of this option, or null if not set.
     * @return the default value or null if none.
     */
    public T getDefaultValue() {
		return defaultValue;
	}

	public String getDescription() {
		return description;
	}

	public String getKey() {
		return key;
	}

    public char getShortKey() {
        return shortKey;
    }

    public boolean isOptional() {
        return optional;
    }

    public boolean doSkipChecks() {
        return skipChecks;
    }

    /**
     * Set to true to disable correctness checks of arguments, i.e. for 'help' and 'version' options.
     *
     * @param skipChecks set skipCheck flag.
     * @return a reference to this object for chaining.
     */
    public Option<T> setSkipChecks(boolean skipChecks) {
        this.skipChecks = skipChecks;
        return this;
    }

    public Option<T> setShortKey(char shortKey) {
        this.shortKey = shortKey;
        return this;
    }

	public abstract T parse(String s) throws IllegalArgumentException;

    /**
     * Check if this option is enabled (i.e. has been set to a value not equal to null or 'false',
     * depending on the type of the option).
     *
     * @param options a reference to the OptionGroup containing this option.
     * @return true if this option has been set with a non-zero value.
     */
    public boolean isEnabled(OptionGroup options) {
        return options.hasValue(this);
    }

	public String toString() {
		return toString(0, null);
	}

	public String toString(int lAdjust, OptionGroup options) {
		StringBuffer s = new StringBuffer("  ");
        if ( shortKey != SHORT_NONE ) {
            s.append('-');
            s.append(shortKey);
            s.append(",--");
        } else {
            s.append("   --");
        }        
        s.append(key);
        
		for(int i = s.length(); i <= lAdjust; i++) {
			s.append(' ');
		}
		s.append("  ");
		s.append(descrString(options));
		return s.toString();
	}

	public String descrString(OptionGroup options) {

        T defaultValue = options != null ? options.getDefaultValue(this) : this.defaultValue;

		StringBuffer s = new StringBuffer(this.description);
		s.append(" ");
        s.append(getDefaultsText(defaultValue));
		return s.toString();
	}

    protected String getDefaultsText(T defaultValue) {
        StringBuffer s = new StringBuffer();
        if(defaultValue != null) {
            s.append("[default: ").append(defaultValue).append("]");
        } else {
            s.append(this.optional ? "[optional]" : "[mandatory]");
        }
        return s.toString();
    }
}