package framework;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class GraphSetting extends WindowSetting {
    
	public XYSeries series;
    public XYSeriesCollection dataset;
        
    public GraphSetting() {
        graphSetting();
    }
    
    public void graphSetting() {
   
    	// 차트 데이터 세트 생성
        series = new XYSeries("Hoseo Coin Prices");
        dataset = new XYSeriesCollection(series);
        
        // 차트 생성 	
        JFreeChart chart = ChartFactory.createXYLineChart (
                "HOSEO COIN",
                "TIME",
                "market price",
                dataset
        );
        
        // 그래프 진행 설정
        XYPlot plot = chart.getXYPlot();
        NumberAxis Axis = (NumberAxis) plot.getDomainAxis();
        Axis.setFixedAutoRange(35); // 한 화면에 35개의 정보 담기
        
        // chartPanel을 JPanel에 추가
        JPanel content = new JPanel(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel, BorderLayout.CENTER);
        ghFrame.setContentPane(content);      
    }
    
    // 데이터를 받아서 그래프를 그리는 함수
    public void addValue(double x, double y) {
    	
    	// y-x : 코인 값만 출력, / 100 : 비율 조정
     	double temp_y = (y-x)/100;
     	series.add(x, temp_y);
    }
}
