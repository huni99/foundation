const deleteCart= document.querySelector("#deleteCart");

deleteCart.addEventListener("click",()=>{
const params = new URLSearchParams();
		
	const checked = document.querySelectorAll(".check:checked");

	 if (checked.length === 0) {
	   alert("하나 이상 체크해주세요.");
	   return;
	 }
		checked.forEach(e=>{
				params.append("list",e.value);
			
			})	
			fetch("/dog/cartDelete",{
					method:"post",
					body:params
					
				}).then(r=>r.json())
				  .then(r=>{
					if(r>0){
						alert("삭제되었습니다.")
						location.href="/dog/cartList";	
					}
				  })	
})

