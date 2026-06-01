Phần 1 – Phân tích logic
* Khác biệt giữa CSRF trong web truyền thống và REST API:

Ứng dụng web truyền thống (session-based):

Người dùng đăng nhập, server tạo session và lưu trong cookie.

CSRF bảo vệ bằng cách yêu cầu một token duy nhất (CSRF token) trong form POST/PUT/DELETE để đảm bảo request thực sự đến
từ trang web hợp lệ.

Nếu không có token hoặc token sai → từ chối request.

Ứng dụng REST API (stateless/token-based):

Client (ví dụ mobile app) không dùng session/cookie, mà gửi JWT hoặc Bearer token trong header.

CSRF ít liên quan vì không có cookie tự động gửi kèm. Thay vào đó, nguy cơ chính là token bị đánh cắp.

Do đó, bảo vệ CSRF truyền thống không phù hợp, thường gây lỗi 403 hoặc redirect không mong muốn.

* Nguy cơ khi vô hiệu hóa CSRF một cách mù quáng:

Nếu ứng dụng vẫn có phần web truyền thống (form login, quản trị web), việc tắt CSRF hoàn toàn sẽ mở cửa cho tấn công
CSRF: hacker có thể tạo form giả, ép trình duyệt người dùng gửi request nguy hiểm (ví dụ: xoá dữ liệu, chuyển tiền).

Vì vậy, phải tùy chỉnh: hoặc chỉ vô hiệu hóa cho API stateless, hoặc dùng cơ chế CSRF token phù hợp với REST API (ví dụ
CookieCsrfTokenRepository).