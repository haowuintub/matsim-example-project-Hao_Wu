/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.project;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.pt.utils.TransitScheduleValidator;
import org.matsim.vehicles.Vehicle;
import org.matsim.vehicles.VehicleType;
import org.matsim.vehicles.VehiclesFactory;

/**
 * @author nagel
 *
 */
public class RunMatsim_UB9_pt {
	private final static Logger LOG = Logger.getLogger(RunMatsim_UB9_pt.class);

	public static void main(String[] args) {

		Config config = ConfigUtils.loadConfig( "scenarios/pt-tutorial/0.config.xml" ) ;

		config.controler().setLastIteration(1);
		config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);

		// possibly modify config here

		// ---

		Scenario scenario = ScenarioUtils.loadScenario(config) ;

		// possibly modify scenario here
		TransitSchedule transitSchedule = scenario.getTransitSchedule();
		TransitLine blueLine = transitSchedule.getTransitLines().get(Id.create("Blue Line", TransitLine.class));

		TransitRoute rounte1to3 = blueLine.getRoutes().get(Id.create("1to3", TransitRoute.class));

		TransitScheduleFactory tsf = transitSchedule.getFactory();

		Departure departure = tsf.createDeparture(Id.create("newDep", Departure.class), (8 * 60. + 45) * 60.);

		rounte1to3.addDeparture(departure);


		TransitScheduleValidator.ValidationResult result = TransitScheduleValidator.validateAll(transitSchedule, scenario.getNetwork());
/*		for(String error : result.getErrors()){
			System.out.println("Transit scheudle error: " + error);
		}*/
		for(String error : result.getErrors()){
			LOG.warn(error);
		}
		for(String warning : result.getWarnings()){
			LOG.warn(warning);
		}
		// issue is less than warning, more like a recommendation.
		for(TransitScheduleValidator.ValidationResult.ValidationIssue issue : result.getIssues()){
			LOG.warn(issue.getMessage());
		}

		VehiclesFactory vf = scenario.getVehicles().getFactory();
		Id<Vehicle> vehId = Id.createVehicleId("tr_3");

		VehicleType smallTrain = scenario.getTransitVehicles().getVehicleTypes().get(Id.create("1",VehicleType.class));

		Vehicle vehicle = vf.createVehicle(vehId,smallTrain);

		scenario.getTransitVehicles().addVehicle(vehicle);


		departure.setVehicleId(vehId);

		TransitScheduleWriter tsw = new TransitScheduleWriter(transitSchedule);
		tsw.writeFile("scenarios/pt-tutorial/transitScheduleEnhanced.xml");



		// ---

		Controler controler = new Controler( scenario ) ;

		// possibly modify controler here

//		controler.addOverridingModule( new OTFVisLiveModule() ) ;

		// ---

		controler.run();
	}

}