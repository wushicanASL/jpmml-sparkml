/*
 * Copyright (c) 2019 Villu Ruusmann
 *
 * This file is part of JPMML-SparkML
 *
 * JPMML-SparkML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-SparkML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-SparkML.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpmml.sparkml;

import java.util.List;
import java.util.Objects;

import org.jpmml.converter.CategoricalLabel;
import org.jpmml.converter.Feature;
import org.jpmml.converter.Label;
import org.jpmml.converter.Schema;

public class SchemaUtil {

	private SchemaUtil(){
	}

	static
	public void checkSchema(Schema schema){
		Label label = schema.getLabel();
		List<? extends Feature> features = schema.getFeatures();

		if(label != null){

			for(Feature feature : features){

				if(Objects.equals(label.getName(), feature.getName())){
					throw new IllegalArgumentException("Label column '" + label.getName() + "' is contained in the list of feature columns");
				}
			}
		}
	}

	static
	public void checkSize(int size, CategoricalLabel categoricalLabel){

		if(categoricalLabel.size() != size){
			throw new IllegalArgumentException("Expected " + size + " target categories, got " + categoricalLabel.size() + " target categories");
		}
	}

	static
	public void checkSize(int size, List<? extends Feature> features){

		if(features.size() != size){
			throw new IllegalArgumentException("Expected " + size + " feature(s), got " + features.size() + " feature(s)");
		}
	}
}