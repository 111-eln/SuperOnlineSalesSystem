![image](https://github.com/user-attachments/assets/e9938534-bbf8-40ec-9077-3979be096297)


Senaryo Akışı
Önce tüm databaseler temiz şekilde oluşturulur.Sonrasında catalog servise ürün eklenir eklenen ürünün stoğu varsa bu bilgi rabbitmq ile stok servise iletilir ve katalog servise eklenen ürün stok servise de eklenmiş olur.  Ardından corder servista katalog serviste yer alan bir ürünü satın almak için ürün bilgileri ve ödeme bilgileri girilir . Ürün ismi gRPC stok servise göderilir eğer ürün stoğu 1den büyükse stok bir azaltılır ve order servise mesaj dönülür.Eğer ürün stoğu bir ise yapılan orderla beraber stok sıfırlanacağı için önce ordera ürün stoğu yeterli mesajı dönülür ardından son ürünün satın alındığı ve stoğun tükendiği bilgisini alan order servis ürünün catalog servisten slinmesi için katalog servise gRPC isteği gönderir.Mesajla gelen ürün ismi referansıyla katalog servisi ürünü katalogdan siler.


