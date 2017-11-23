#include "DHT.h"

#define DHTPINEXT 2
#define DHTPININT 4
#define PELTIERPIN 3

DHT dhtint(DHTPININT, DHT22); // initializing intern DHT sensor
DHT dhtext(DHTPINEXT, DHT22); // initializing extern DHT sensor

// initializing setpoint as global variable because we have to initialise it at 0 but not have to reset at every loop
int cons = 30;

void setup()
{
    Serial.begin(9600);
    dhtint.begin();
    dhtext.begin();
}

void loop()
{
    float hum = 0;
    float tempint = 0;
    float tempext = 0;
    float huminu = 0;
    float poro = 0;
    boolean alerte = false;
    
    sensor(&hum, &tempint, "inte");
    sensor(&huminu, &tempext, "exte");
    poro = magnus(tempint, hum);
    if(tempint <= poro)
        alerte = true;

    if(Serial.available())
    {
        // Debug Serial.println("If");
        cons = consigne();
        peltier(cons, tempint);
    }
    
    //Serial.println(cons);
    Serial.print("Temperature interieure ");
    Serial.println(tempint);
    Serial.print("Temperature exterieure ");
    Serial.println(tempext);
    Serial.print("Point de rosee ");
    Serial.println(poro);
    Serial.print("Alerte condensation ");
    Serial.println(alerte);
    Serial.println("");
    delay(2000);
      
}

void peltier(char option, int temp)
{
    int power = 0; //power level 0 to 99%
    int peltier_level = 0;
    if(option == '+') 
        power -= 10;
    else if(option == '-')
        power += 10;
    else if(option >= 15 && option <= 25)
        power = map(option, 0, 99, 25, 15);

    if(power > 99) power = 99;
    if(power < 0) power = 0;

    peltier_level = map(power, 0, 99, 0, 255); //This is a value from 0 to 255 that actually controls the MOSFET

    //Debug
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

void sensor(float *hum, float *temp, String capt)
{
  
    if(capt.equals("inte"))
    {
        *hum = dhtint.readHumidity(); // reading the humidity from the sensor
        *temp = dhtint.readTemperature(); // reading the temperature from the sensor

        if (isnan(*hum) || isnan(*temp)) // checking if read failed
        {
            Serial.println("Impossible de se connecter au capteur DHT22 interieur!!!");
            return;
        }
    }
    else if(capt == "exte")
    {
         *temp = dhtext.readTemperature();
         if(isnan(*temp))
         {
              Serial.println("Impossible de se connecter au capteur DHT22 exterieur!!!");
         }
    }
      /* Debug
      Serial.print("Humidité: ");
      Serial.println(hum);
      //Serial.println("%");
      Serial.print("Température: ");
      Serial.println(temp);
      Serial.println("");*/
}
