#include "DHT.h"

#define PIN 2

DHT dht(PIN, DHT22); // initializing DHT sensor

void setup()
{
    Serial.begin(9600);
    dht.begin();
}

void loop()
{
    float hum = dht.readHumidity(); // reading the humidity from the sensor
    float temp = dht.readTemperature(); // reading the temperature from the sensor

    if(isnan(hum) || isnan(temp)) // checking if read failed
    {
        Serial.println("Failed to read from DHT sensor!!!");
        return;
    }

    // Debug
    Serial.print("Humidité: ");
    Serial.print(hum);
    Serial.println("%");
    // Debug
    Serial.print("Température: ");
    Serial.print(temp);
    Serial.println("*C");
    Serial.println("");
    
    delay(2000);
}
