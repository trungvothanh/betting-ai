package dk.bettingai.marketsimulator.trader

import dk.bettingai.marketsimulator.betex.api._
import IMarket._
import IBet.BetTypeEnum._
import java.util.Date
import ITrader._

/**This trait represents a trader that can place bets on a betting exchange.
 * 
 * @author korzekwad
 *
 */
object ITrader {
	
	/**Provides market data and market operations that can be used by trader to place bets on a betting exchange market.*/
	trait ITraderContext {
		
	val marketId:Long
	val marketName:String
	val eventName:String
	val numOfWinners:Int
	val marketTime:Date
	val runners:List[IRunner]
		
	/**Set labels for all chart series.*/
	def setChartLabels(chartLabels:List[String])
			
	/**Add chart values to time line chart. Key - time stamp, value - list of values for all series in the same order as labels.*/
	def addChartValues(chartValues:Tuple2[Long,List[Double]])
	
	/**Returns best toBack/toLay prices for market runner.
	* Element 1 - best price to back, element 2 - best price to lay
	* Double.NaN is returned if price is not available.
	* @return 
	* */
	def getBestPrices(runnerId: Long): Tuple2[Double,Double]
	
	/**Returns best toBack/toLay prices for market.
	 * 
	 * @return Key - runnerId, Value - market prices (element 1 - priceToBack, element 2 - priceToLay)
	 */
	def getBestPrices():Map[Long,Tuple2[Double,Double]]
	
	/** Places a bet on a betting exchange market.
	* 
	* @param betSize
	* @param betPrice
	* @param betType
	* @param runnerId
	*/
	def placeBet(betSize:Double, betPrice:Double, betType:BetTypeEnum, runnerId:Long)
	
	/**Returns all bets placed by user on that market.
	 *
	 *@param matchedBetsOnly If true then matched bets are returned only, 
	 * otherwise all unmatched and matched bets for user are returned.
	 */
	def getBets(matchedBetsOnly:Boolean):List[IBet]
	
	/** Returns total unmatched volume to back and to lay at all prices for all runners in a market on a betting exchange. 
	 *  Prices with zero volume are not returned by this method.
   * 
   * @param runnerId Unique runner id that runner prices are returned for.
   * @return
   */
	def getRunnerPrices(runnerId:Long):List[IRunnerPrice]
	
	/**Returns total traded volume for all prices on all runners in a market.*/
	def getRunnerTradedVolume(runnerId:Long): List[IPriceTradedVolume]
	
	}
}
trait ITrader {

	/**It is called once on trader initialisation.
	 * 
	 * @param ctx Provides market data and market operations that can be used by trader to place bets on a betting exchange market.
	 * */
	def init(ctx: ITraderContext)
	
	/**Executes trader implementation so it can analyse market on a betting exchange and take appropriate bet placement decisions.
	 * 
	 * @param eventTimestamp Time stamp of market event
	 * @param ctx Provides market data and market operations that can be used by trader to place bets on a betting exchange market.
	 */
	def execute(eventTimestamp:Long,ctx: ITraderContext)
	

}