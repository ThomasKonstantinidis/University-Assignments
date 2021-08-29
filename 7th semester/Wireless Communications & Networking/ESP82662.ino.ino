#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#define PHONE "PHONE"
#define FRIEND "ThomasCommand"
const char* ssid = "****";//Your own wifi ssid 
//const char* ssid = "iPhone";//Your own wifi ssid here
const char* password = "****";// Your wifi password here
//const char* password = "****";// Your wifi password here
//const char* mqtt_server = "broker.mqttdashboard.com";// Your mqtt server
const char* mqtt_server = "****";

WiFiClient esp2Client;
PubSubClient client(esp2Client);
long lastMsg = 0;
char msg[50];
int value = 0;


void setup_wifi() {
   delay(100);
  // We start by connecting to a WiFi network
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
  String wrote;
  for(int i=0;i<length;i++)
  {
    Serial.print((char)payload[i]);  
    wrote += ((char)payload[i]);
    if(wrote == "COMPARE"){
      wrote = "TEST";
      client.publish(FRIEND, wrote.c_str(), true);
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
    String clientId = "ESP8266Client2-";
    clientId += String(random(0xffff), HEX);
    // Attempt to connect
    if (client.connect(clientId.c_str()))
    {
      Serial.println("connected");
     //once connected to MQTT broker, subscribe command if any
      client.subscribe("ESP2");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 6 seconds before retrying
      delay(6000);
    }
  }
} //end reconnect()

void setup() {
  Serial.begin(115200);
  setup_wifi();
  client.setServer(mqtt_server, 1883);
}

void loop() {
  if (!client.connected()) {
    reconnect();
  }
  
  client.setCallback(callback);
  client.loop();

}
