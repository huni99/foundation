const btn_cart= document.querySelector("#btn-cart");
const btn_adopt= document.querySelector("#btn-adopt");
const frm = document.getElementById("frm");

btn_cart.addEventListener("click",()=>{
	const value = document.querySelector("#value");
	const params = new URLSearchParams();
	params.append("dogNo",value.value);
	fetch("/dog/cartAdd",{
		method:"post",
		body:params		
	}).then(r=>r.json())
	  .then(r=>{
		if(r>0){
			if(confirm("즐겨찾기로 이동하시겠습니까?")){
				location.href="/dog/cartList";
			}
		}
		else{
			alert("이미 즐겨찾기에 존재합니다.");
		}
		
	  })
})
btn_adopt.addEventListener("click",()=>{
	if(confirm("입양하시겠습니까?")){
		const value= document.querySelector("#value");
		const params = new URLSearchParams();
		params.append("dogNo",value.value);
		fetch("/dog/adopt",{
			method:"post",
			body:params
			
		}).then(r=>r.json())
		  .then(r=>{
			
			location.href=r.next_redirect_pc_url;
		  })
		  
		  
			
	}
	
	
})

