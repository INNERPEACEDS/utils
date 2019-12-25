package com.wgb.utils.util.xml.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/12/18 15:50
 */
@XmlRootElement(name = "world")
public class World {

	private List<Human> humans;

	private Forest forest;

	@XmlElements(@XmlElement(name = "human", type = Human.class))
	public List<Human> getHumans() {
		return humans;
	}

	public void setHumans(List<Human> humans) {
		this.humans = humans;
	}

	@XmlElement
	public Forest getForest() {
		return forest;
	}

	public void setForest(Forest forest) {
		this.forest = forest;
	}
}
