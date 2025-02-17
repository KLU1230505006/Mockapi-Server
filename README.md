Weather Data App

Weather Data App, MockAPI.io üzerinden hava durumu verilerini çeken ve bir web arayüzünde görüntüleyen basit bir HTTP sunucusu ve istemci uygulamasıdır.

Proje İçeriği

Bu proje üç ana bileşenden oluşur:

Java HTTP Sunucusu: com.sun.net.httpserver.HttpServer kullanarak bir HTTP sunucusu oluşturur.

MockAPI.io Entegrasyonu: MockAPI.io üzerinden hava durumu verilerini alır ve istemcilere sunar.

Web Arayüzü: HTML, CSS ve JavaScript kullanarak gelen verileri tablo halinde gösterir.

Kullanılan Teknolojiler

Java 8

Maven (Bağımlılık yönetimi için)

Gson (JSON işleme için)

MockAPI.io (Sahte API verisi sağlamak için)

HTML, CSS ve JavaScript (Web arayüzü için)

Kurulum ve Çalıştırma

1. Depoyu Klonlayın git clone https://github.com/kullaniciadi/WeatherDataApp.git cd WeatherDataApp
2.Bağımlılıkları Yükleyin Maven kullanarak bağımlılıkları yükleyin: mvn clean install
3. Sunucuyu Başlatın mvn exec:java -Dexec.mainClass="Server"
4. Web Arayüzünü Açın http://localhost:8000/data
5. API Entegrasyonu https://67532bf3f3754fcea7bb0a7d.mockapi.io/WeatherData

Örnek API Yanıtı:
[
  {
    "id": "1",
    "name": "Cloudy",
    "createdAt": "2024-02-17T10:00:00.000Z",
    "avatar": "https://example.com/cloudy.png"
  }
]
