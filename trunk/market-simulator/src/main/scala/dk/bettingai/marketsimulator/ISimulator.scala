package dk.bettingai.marketsimulator

import ISimulator._
import scala.io._
import dk.bettingai.marketsimulator.trader._

/**This trait represents a simulator that processes market events, analyses trader implementation and returns analysis report for trader implementation.
 * 
 * @author korzekwad
 *
 */
object ISimulator {
	trait IMarketRiskReport {
		val marketId:Long
		val marketName:String
		val eventName:String
		/**Market expected profit based on bets and market probabilities.*/
		val expectedProfit:Double
		val matchedBetsNumber:Long
		val unmatchedBetsNumber:Long
	}
}
trait ISimulator {

	/** Processes market events, analyses trader implementation and returns analysis report for trader implementation.
	 * 
	 * @param Contains market events that the market simulation is executed for.
	 * @param trader
	 * @param traderUserId
	 * @param historicalDataUserId
	 * @param p Progress listener. Value between 0% and 100% is passed as an function argument.
	 */
	def runSimulation(marketData:Source, trader:ITrader,traderUserId:Int,historicalDataUserId:Int,p: (Int) => Unit):List[IMarketRiskReport]
}