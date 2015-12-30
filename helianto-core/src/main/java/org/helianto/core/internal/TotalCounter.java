package org.helianto.core.internal;

import java.io.Serializable;
import java.util.List;

/**
 * Total counter.
 * 
 * @author mauriciofernandesdecastro
 */
public class TotalCounter 
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private long total = 0;
	
	private long parentTotal = 0;
	
	/**
	 * Constructor.
	 */
	public TotalCounter() {
		super();
	}

	/**
	 * Constructor.
	 */
	public TotalCounter(long total, long parentTotal) {
		this();
		setTotal(total);
		setParentTotal(parentTotal);
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

	public long getParentTotal() {
		return parentTotal;
	}
	public void setParentTotal(long parentTotal) {
		this.parentTotal = parentTotal;
	}

	/**
	 * Helper method to summarize from a list of simple counters.
	 * 
	 * @param stats
	 */
	public static TotalCounter summarize(List<SimpleCounter> stats) {
		long total = 0;
		long parentTotal = 0;
		for (SimpleCounter c: stats) {
			parentTotal++;
			total += c.getItemCount();
		}
		return new TotalCounter(total, parentTotal);
	}

}
