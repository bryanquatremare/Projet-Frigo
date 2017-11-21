#include "DHT.h"

#define PIN 2

DHT dht(PIN, DHT22); // initializing DHT sensor

// initializing setpoint as global variable because we have to initialise it at 0 but not have to reset at every loop
int cons = 25;

void setup()
{
    Serial.begin(9600);
    dht.begin();
}

void loop()
{
    float hum = 0;
    float temp = 0;
    float poro = 0;
    boolean alerte = false;
    if(Serial.available())
    {
        cons = consigne();
    }
    sensor(&hum, &temp);
    poro = magnus(temp, hum);
    if(temp <= poro)
        alerte = true;
    
    Serial.println(cons);
    Serial.println(temp);
    Serial.println(poro);
    Serial.println(alerte);
    Serial.println("");
    delay(2000);
      
}

float magnus(float temp, float hum)
{
    return (237.7 * ( 17.27 * temp / (237.7 + temp) + log(hum/100)) / (17.27 - (17.27 * temp / (237.7 + temp) + log(hum/100))));
}

int consigne()
{
    char buffer[] = {' ',' ',' '};
    Serial.readBytesUntil('n', buffer, 3);
    cons = atoi(buffer);
    /*Debug
    Serial.println(cons);*/
    
    return cons;
}

void sensor(float *hum, float *temp)
{
    *hum = dht.readHumidity(); // reading the humidity from the sensor
    *temp = dht.readTemperature(); // reading the temperature from the sensor

    if (isnan(*hum) || isnan(*temp)) // checking if read failed
    {
      Serial.println("Impossible de se connecter au capteur DHT22!!!");
      return;
    }
      /* Debug
      Serial.print("Humidité: ");
      Serial.println(hum);
      //Serial.println("%");
      Serial.print("Température: ");
      Serial.println(temp);
      Serial.println("");*/
}
