/**
 *  domoticzSensor "Illuminance Measurement" component
 *
 *  Copyright 2018 Martin Verbeek
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "domoticzSensor Temperature Measurement", namespace: "verbem", author: "Martin Verbeek", vid:"generic-temperature") {
		capability "Temperature Measurement"
        capability "Health Check"
		capability "Sensor"
	}

	tiles
    {
		standardTile("sensorTemperature", "device.temperature", decoration: "flat", width: 2, height: 2) {
			state "temperature", label: '${currentValue}', icon: "http://cdn.device-icons.smartthings.com/Weather/weather2-icn@2x.png"
			state "Error", label: "Install Error", backgroundColor: "#bc2323"
		}        
		main (["sensorTemperature"])
		details(["sensorTemperature"])
	}
}

def installed() {
	initialize()
}

def updated() {
	initialize()
}

def initialize() {

    try {
    if (parent) {
        sendEvent(name: "temperature", value: 0)
    }
    else {
    	log.error "You cannot use this DTH without the related DTH domoticzSensor, the device needs to be a child of this DTH"
        sendEvent(name: "temperature", value: "Error", descriptionText: "$device.displayName You cannot use this DTH without the related domoticzSensor DTH", isStateChange: true)
    }
  }
  catch (e) {
  	log.error e
  }
}