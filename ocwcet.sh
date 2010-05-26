#! /bin/sh
mkdir -p ocache_eval
# The lift benchmark

# Configuration SRAM (2*w)
make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
   WCET_OPTIONS="-dataflow-analysis -object-cache-analysis -jop-ocache-access-cycles 2"
cp java/target/wcet/wcet.StartLift_measure/ocache_eval.txt ocache_eval/lift_2_0.txt

# Configuration SDRAM (10+2*w) with max_burst = 4 words
make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
   WCET_OPTIONS="-dataflow-analysis -object-cache-analysis -jop-ocache-access-cycles 2 -jop-ocache-access-delay 10 -jop-ocache-max-burst 4"
cp java/target/wcet/wcet.StartLift_measure/ocache_eval.txt ocache_eval/lift_2_10_4.txt

exit;

#make java_app wcet P1=bench P2=scd_micro P3=Main WCET_METHOD="scd_micro.Motion.findIntersection(Lscd_micro/Motion)" \
make java_app wcet P1=bench P2=scd_micro P3=Main WCET_METHOD="measure" \
  TARGET_JDK=jdk16mod \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"

exit;

make java_app wcet P1=test P2=wcet/devel P3=Simple WCET_METHOD=measure3 \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis -jop-ocache-line-size 1"

exit;

make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis -jop-ocache-line-size 1"

exit;

make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"


make java_app wcet P1=common P2=ttpa/demo P3=Main WCET_METHOD=ttpa.protocol.Node.doMpSlotAction \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"


make java_app wcet P1=paper/trading/plain P2=com/sun/oss/trader P3=Main WCET_METHOD="com.sun.oss.trader.tradingengine.OrderManager.checkForTrade(Lcom/sun/oss/trader/data/OrderEntry;D)Z" \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"



# jolden is not in the source
make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis -jop-ocache-line-size 1"

exit;

make java_app wcet P1=bench P2=scd_micro P3=Main WCET_METHOD="scd_micro.Motion.findIntersection(Lscd_micro/Motion)" \
  TARGET_JDK?=jdk16mod \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"


make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"


make java_app wcet P1=common P2=ttpa/demo P3=Main WCET_METHOD=ttpa.protocol.Node.doMpSlotAction \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"


make java_app wcet P1=paper/trading/plain P2=com/sun/oss/trader P3=Main WCET_METHOD="com.sun.oss.trader.tradingengine.OrderManager.checkForTrade(Lcom/sun/oss/trader/data/OrderEntry;D)Z" \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"



# jolden is not in the source
make java_app wcet P1=bench P2=jolden/mst P3=MST WCET_METHOD=main \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis -object-cache-fields"
make java_app wcet P1=bench P2=jolden/mst P3=MST WCET_METHOD=main \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"





exit;

make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis -object-cache-fields"
make java_app wcet P1=test P2=wcet P3=StartLift WCET_METHOD=measure \
  WCET_OPTIONS="-dataflow-analysis -object-cache-analysis"



