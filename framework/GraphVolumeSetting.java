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


public class GraphVolumeSetting extends WindowSetting {
    
	public XYSeries seriesV;
    public XYSeriesCollection datasetV;
        
    public GraphVolumeSetting() {
        graphSetting();
    }
    
    public void graphSetting() {
   
    	// 차트 데이터 세트 생성
        seriesV = new XYSeries("Hoseo Coin Volume");
        datasetV = new XYSeriesCollection(seriesV);
        
        // 차트 생성 	
        JFreeChart chart = ChartFactory.createXYLineChart (
                "HOSEO COIN VOLUME",
                "TIME",
                "VOLUME",
                datasetV
        );
        
        // 그래프 진행 설정
        XYPlot plot = chart.getXYPlot();
        NumberAxis Axis = (NumberAxis) plot.getDomainAxis();
        Axis.setFixedAutoRange(15); // 한 화면에 15개의 정보 담기
        
        // chartPanel을 JPanel에 추가
        JPanel content = new JPanel(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel, BorderLayout.CENTER);
        ghVFrame.setContentPane(content);      
    }
    
    // 데이터를 받아서 그래프를 그리는 함수
    public void addVolume(double x, double y) {
    	
    	// y-x : 거래량만 출력
     	double temp_y = (y-x);
     	seriesV.add(x, temp_y);
    }
}

