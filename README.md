![image](https://github.com/user-attachments/assets/28ffe857-dd67-436c-a7b6-0f977c32319e)


Senaryo Akışı
Önce tüm databaseler temiz şekilde oluşturulur.Sonrasında catalog servise ürün eklenir eklenen ürünün stoğu varsa bu bilgi rabbitmq ile stok servise iletilir ve katalog servise eklenen ürün stok servise de eklenmiş olur.  Ardından corder servista katalog serviste yer alan bir ürünü satın almak için ürün bilgileri ve ödeme bilgileri girilir . Ürün ismi gRPC stok servise göderilir eğer ürün stoğu 1den büyükse stok bir azaltılır ve order servise mesaj dönülür.Eğer ürün stoğu bir ise yapılan orderla beraber stok sıfırlanacağı için önce ordera ürün stoğu yeterli mesajı dönülür ardından son ürünün satın alındığı ve stoğun tükendiği bilgisini alan order servis ürünün catalog servisten slinmesi için katalog servise gRPC isteği gönderir.Mesajla gelen ürün ismi referansıyla katalog servisi ürünü katalogdan siler.Stoğun yeterli olduğu senaryodan devam edildiği zaman yeterli stok mesajını alan order servis ödeme servisine kart bilgilerini webClient ile http post  isteği olarak gönderir.Ödeme servisi gelen post isteğini success entitysini true false random fonksiyonu ile elde edip gönderir.Ödeme servisi gerçek bir banka onayı ya da reddi elde etmez mock bir sistem olarak tasarlanmıştır.Ödeme servisinden ödeme onayı bilgisi order servise verilirse bildirim servisinden veri tabanına ilgili ürünün satışının yapıldığına dair siparişin tarihi ile beraber log atılır



