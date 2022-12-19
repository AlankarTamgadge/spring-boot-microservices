package com.example.limits.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class LimitsConfiguration {

	private int maximum;
	private int manimum;

	public LimitsConfiguration() {

	}

	public LimitsConfiguration(int maximum, int manimum) {
		super();
		this.maximum = maximum;
		this.manimum = manimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public int getManimum() {
		return manimum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public void setManimum(int manimum) {
		this.manimum = manimum;
	}

}
