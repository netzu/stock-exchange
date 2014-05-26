package indicators.movingaverage.simple;

import java.util.List;

import org.joda.time.DateTime;

import data.collector.StockTickerHistory;

/*
 * 
 * Istnieją trzy sposoby interpretacji wskazań średnich kroczących:
 * 
 * 1. Pierwszy z nich (przedstawiony na wykresie wyżej) polega na obserwacji punktów przecięcia średniej kroczącej przez wykres kursu. Jeżeli wykres kursu 
 * przecina od góry nierosnącą linię średniej kroczącej, jest to sygnał do sprzedaży (S). Gdy zaś wykres kursu przecina od dołu niemalejącą średnią kroczącą, 
 * jest to sygnał kupna (K). Należy jednak pamiętać o uwadze zamieszczonej wyżej i dotyczącej odpowiedniego ustawienia parametru średniej kroczącej. Sygnały 
 * generowane przez średnie są tym mocniejsze, im dłużej linia średniej była ustabilizowana w pozycji zbliżonej do horyzontalnej przed wygenerowaniem sygnału.
 * 
 * 2.Drugi sposób (patrz powyższy wykres) polega na zastosowaniu dwóch lub trzech średnich kroczących o różnych parametrach (np. 15-sesyjna i 45 sesyjna lub 
 * 15-sesyjna, 30-sesyjna i 45-sesyjna) i obserwacji punktów przecięcia tych średnich przez wykres kursu. Jeśli wykres kursu przecina od dołu niemalejące średnie, 
 * jest to mocny sygnał kupna (K), zaś gdy kurs przebija od góry nierosnące średnie jest to mocny sygnał sprzedaży (S)
 * 
 * 3. Ostatnim sposobem jest obserwacja przecinania się samych średnich (wykres powyżej). W tym przypadku przecięcie od dołu niemalejącej 'dłuższej' średniej 
 * (np. 30-sesyjnej i/lub 45-sesyjnej) przez 'krótszą' średnią (np. 15-sesyjną) daje mocny sygnał do zakupu (K). Analogicznie, przecięcie od góry nierosnącej 
 * 'dłuższej' średniej przez 'krótszą' średnią daje mocny sygnał sprzedaży (S). Należy jednak pamiętać, że sygnały generowane przy tym sposobie interpretacji są 
 * często opóźnione i dlatego lepiej nadają się do inwestycji średnio- i długoterminowych
 * 
 */

public class SimpleMovingAverageSignals {
	
	public List<DateTime> getBuySignal(List<SimpleMovingAverageData> averageCollection, StockTickerHistory stockCollection){
		BuySignalsGenerator buySignals = new BuySignalsGenerator();
		return buySignals.generate(averageCollection, stockCollection);
	}
	
	public List<DateTime> getSellSignals(List<SimpleMovingAverageData> averageCollection, StockTickerHistory stockCollection){
		SellSignalsGenerator sellSignals = new SellSignalsGenerator();
		return sellSignals.generate(averageCollection, stockCollection);
	}
}
