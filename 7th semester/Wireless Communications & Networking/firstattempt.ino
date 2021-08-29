#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27, 20, 4);/*0x27 means the address of this 1602 I2C LCD display*/

#define PHONE "PHONE"
#define FRIEND "ESP2"
#define PC "PC"

const char* ssid = "****";
//const char* ssid = "iPhone";
const char* password = "****";
//const char* password = "****";
//const char* mqtt_server = "broker.mqttdashboard.com";// choose mqtt server
const char* mqtt_server = "****";
//const char* mqtt_server = "iot.eclipse.org";
unsigned long start, finished;
float elapsed;
WiFiClient espClient;
PubSubClient client(espClient);
long lastMsg = 0;
String stat;
char stats[100];
int value = 0;


void setup_wifi() {
   delay(100);
  // Connecting to a WiFi network
    Serial.print("Connecting to ");
    Serial.println(ssid);
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) 
    {
      delay(500);
      Serial.print(".");
    }
  randomSeed(micros());
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void callback(char* topic, byte* payload, unsigned int length) 
{
  Serial.print("Command from MQTT broker is : ");
  Serial.print(topic);
  Serial.println();
  Serial.print(" publish data is:");
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("THOMAS LCD"); // Start Print text to Line 1
  String wrote;
  for(int i=0;i<length;i++)
  {
    Serial.print((char)payload[i]);  
    lcd.setCursor(i, 1);
    lcd.write((char)payload[i]);
    wrote += ((char)payload[i]);
    if(wrote == "EVALUATE"){
      delay(800);
      lcd.clear();
      lcd.setCursor(0, 0);
      start = millis();
      client.publish(FRIEND, "COMPARE" , true);
      lcd.print("Test began");
      
    }
    if(wrote == "TEST"){
      finished = millis();
      elapsed = (((float)(finished - start))/1000)-1;
      lcd.clear();
      lcd.setCursor(0,0);
      lcd.print("Elapsed time is:");
      lcd.setCursor(0,1);
      lcd.print(elapsed);  
      lcd.print(" sec.");
    }
  } 
  Serial.println();
} //end callback

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) 
  {
    Serial.print("Attempting MQTT connection...");
    // Create a random client ID
    String clientId = "ESP8266Client-";
    clientId += String(random(0xffff), HEX);
    // Attempt to connect
    if (client.connect(clientId.c_str()))
    {
      Serial.println("connected");
     //once connected to MQTT broker, subscribe command if any
      client.subscribe("ThomasCommand");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("THOMAS LCD"); 
  }
} //end reconnect()

void setup() {
  Serial.begin(115200);
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  lcd.init();   // initializing the LCD
  lcd.backlight(); // Enable or Turn On the backlight 
}

void loop() {
  if (!client.connected()) {
    reconnect();
  }
  client.setCallback(callback);
  stat = WiFi.macAddress().c_str();
  stat.toCharArray(stats, 100);
  client.publish(PC, stats);
  delay(1000);
  client.loop();
}
