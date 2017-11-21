/*#include "DHT.h"

#define PIN 2

DHT dht(PIN, DHT22); // initializing DHT sensor

// initializing setpoint as global variable because we have to initialise it at 0 but not have to reset at every loop
int cons = 0;*/

void setup()
{
    Serial.begin(9600);
    //dht.begin();
}

void loop()
{
  
}

int send()
{
    char buffer[] = {' ',' ',' ',' '};
    while(!Serial.available());
    Serial.readBytesUntil('n', buffer, 4);
    int cons = atoi(buffer);
    // Debug
    //Serial.println(cons);

    return cons;
}

float sensor(float hum, float temp)
{
    hum = dht.readHumidity(); // reading the humidity from the sensor
    temp = dht.readTemperature(); // reading the temperature from the sensor

    if (isnan(hum) || isnan(temp)) // checking if read failed
    {
      Serial.println("Failed to read from DHT sensor!!!");
      return;
    }

      // Debug
      Serial.print("Humidité: ");
      Serial.println(hum);
      //Serial.println("%");
      // Debug
      Serial.print("Température: ");
      Serial.println(temp);
      //Serial.println("*C");
      Serial.println("");

      return hum, temp;
}
