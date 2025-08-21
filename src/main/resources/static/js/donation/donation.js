document.addEventListener("DOMContentLoaded", function() {
    const btn = document.getElementById("btn-sponsor");
    if (btn) {
        btn.addEventListener("click", function() {
            const dogNo = btn.getAttribute("data-dogno");

            // 방법1: 컨트롤러 redirect 방식 (바로 결제창 이동)
            window.location.href = "/donation/ready?dogNo=" + dogNo;

            // 방법2: fetch JSON 방식 (ResponseBody로 next_redirect_pc_url 반환 시)
            /*
            fetch("/donation/payReady?dogNo=" + dogNo, { method: "POST" })
                .then(response => response.json())
                .then(data => {
                    if (data.next_redirect_pc_url) {
                        window.location.href = data.next_redirect_pc_url;
                    }
                })
                .catch(err => console.error(err));
            */
        });
    }
});
