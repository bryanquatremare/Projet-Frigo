#include "DHT.h"

#define DHTPIN 2
#define PELTIERPIN 3

DHT dht(DHTPIN, DHT22); // initializing DHT sensor

// initializing setpoint as global variable because we have to initialise it at 0 but not have to reset at every loop
int cons = 30;

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
        peltier(cons);
    }
    
    sensor(&hum, &temp);
    poro = magnus(temp, hum);
    if(temp <= poro)
        alerte = true;
    
    //Serial.println(cons);
    Serial.print("Temperature interieure ");
    Serial.println(temp);
    Serial.println("Temperature exterieure: capteur pas implemente");
    Serial.print("Point de rosee ");
    Serial.println(poro);
    Serial.print("Alerte condensation ");
    Serial.println(alerte);
    Serial.println("");
    delay(2000);
      
}

void peltier(char option)
{
    int power = 0; //power level 0 to 99%
    int peltier_level = map(power, 0, 99, 0, 255); //This is a value from 0 to 255 that actually controls the MOSFET
    if(option == '+') 
        power += 5;
    else if(option == '-')
        power -= 5;
    else if(option >= 0 && option <= 30)
        power = map(option, 0, 99, 0, 30);

    if(power > 99) power = 99;
    if(power < 0) power = 0;

    peltier_level = map(power, 0, 99, 0, 255);
    
    Serial.print("Power=");
    Serial.println(power);
    Serial.print("PLevel=");
    Serial.println(peltier_level);

    analogWrite(PELTIERPIN, peltier_level); //Write this new value out to the port
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
