I. Caching with Redis 
- Lưu ý : Redis thực hiện lưu dữ liệu trên RAM theo dạng " Key - Value "
- Thao tác với dữ liệu trong Redis cực kì nhanh (gấp 100 so với CSDL quan hệ)
Flow khi làm việc với redis 
- Request -> Server -> check Redis trước --- nếu có trong Redis -> thì trả về luôn
                                          |
                                          ---nếu chưa có -> lấy từ DB và lưu lại vào Redis
