package edu.fgcu.stesting.uiesg.data;

public interface UIEfficiencyStatisticType {
	
	String getName();
	
	String getDescription();
	
	UIEfficiencyStatistic calculate( GraphOutputData graph );

}