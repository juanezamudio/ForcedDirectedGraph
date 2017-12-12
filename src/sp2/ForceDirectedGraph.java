/**
 * 
 */
package sp2;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationImageServer;

/**
 * @author jzamudio
 * @version September 14, 2017
 *
 */
public class ForceDirectedGraph {
	
	private Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	public Map<Integer, ArrayList<Integer>> createMap(String file) {
		
		try {
			Scanner scanner = new Scanner (new FileReader(file));
			Integer vertex;
			Integer edge;
			ArrayList<Integer> value;
			
			while (scanner.hasNext()) {
				vertex = scanner.nextInt();
				edge = scanner.nextInt();
				
				if (map.containsKey(vertex)) {
					value = map.get(vertex);
					value.add(edge);
					map.put(vertex, value);
				} else {
					value = new ArrayList<Integer>();
					value.add(edge);
					map.put(vertex, value);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	public void displace (String vertex) {
		
		
		
	}
	
	public Graph<String, String> addToGraph(Graph<String, String> graph, 
										   Map<Integer, ArrayList<Integer>> map) {
		int count = 0;
		
		for (Integer vertices : map.keySet()) {
			String vertex = Integer.toString(vertices);
			graph.addVertex(vertex);
			
			for (Integer edges : map.get(vertices)) {
				String name = "e".concat(Integer.toString(count));
				String edge = Integer.toString(edges);
				
				graph.addEdge(name, new Pair<String>(vertex, edge));
				count++;
			}
		}
		
		return graph;
	}
	
	/**
	 * 
	 * @param graph
	 * @param layout
	 */
	public Layout<String, String> FR(Graph<String, String> graph,
				  					Map<Integer, ArrayList<Integer>> map,
				  					int val) {
		

		Layout<String, String> layout = new StaticLayout<String, String>(graph);
		Graph<String, String> newGraph = this.addToGraph(graph, map);
		Random rand = new Random();
		
		for (String vertices : newGraph.getVertices()) {
			layout.setLocation(vertices, new Point(rand.nextInt(400), rand.nextInt(400)));
		}
		
		for (int i = 1; i <= val; i++) {
			double temperature = 1/i;
			
			for (String vertices : newGraph.getVertices()) {
				
//				vertices.
				
			}
			
		}
		
		
		return layout;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Graph<String, String> g = new SparseGraph<String,String>();

//		g.addVertex("000");
//		g.addVertex("001");
//		g.addVertex("010");
//		g.addVertex("100");
//		g.addVertex("011");
//		g.addVertex("101");
//		g.addVertex("110");
//		g.addVertex("111");
//		g.addEdge("e0", new Pair<String>("000","001"));
//		g.addEdge("e1", new Pair<String>("000","010"));
//		g.addEdge("e2", new Pair<String>("000","100"));
//		g.addEdge("e3", new Pair<String>("001","011"));
//		g.addEdge("e4", new Pair<String>("001","101"));
//		g.addEdge("e5", new Pair<String>("010","011"));
//		g.addEdge("e6", new Pair<String>("010","110"));
//		g.addEdge("e7", new Pair<String>("100","101"));
//		g.addEdge("e8", new Pair<String>("100","110"));
//		g.addEdge("e9", new Pair<String>("011","111"));
//		g.addEdge("e10", new Pair<String>("101","111"));
//		g.addEdge("e11", new Pair<String>("110","111"));

		//TODO: Compute a force directed layout
		ForceDirectedGraph myGraph = new ForceDirectedGraph();
		Map<Integer, ArrayList<Integer>> myMap = myGraph.createMap("karate.txt");
		Layout<String, String> myLayout = myGraph.FR(g, myMap, 1);
		
		Dimension dim = new Dimension(400,400);
		
//		// (0,0) is the top left corner
//		Layout<String,String> l = new StaticLayout<String,String>(g);
//		l.setLocation("000", new Point(100, 50));
//		l.setLocation("001", new Point(300, 50));
//		l.setLocation("010", new Point(100, 350));
//		l.setLocation("100", new Point(50, 100));
//		l.setLocation("011", new Point(300, 350));
//		l.setLocation("101", new Point(350, 100));
//		l.setLocation("110", new Point(50, 300));
//		l.setLocation("111", new Point(350, 300));
//		
		VisualizationImageServer<String,String> vis = new VisualizationImageServer<String,String>(myLayout, dim);
//		
//		BufferedImage im = (BufferedImage) vis.getImage(
//				new Point2D.Double(dim.getWidth()/2, dim.getHeight()/2),
//				dim);
//		ImageIO.write((RenderedImage) im, "jpg", new File("out.jpg"));
	}
}
