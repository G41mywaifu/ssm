<html>
<head>
    <title></title>
    <script>
        function total(Toasternumber,Sandwichnumber){
            var priceToaster,priceSandwich,discount,password;
            priceToaster=10;
            priceSandwich=10;
            price=Toasternumber*priceToaster+Sandwichnumber*priceSandwich;
            password=parseInt(prompt("Please enter the password"))
            if (password="HOLIDAYS2"){
                price=price*0.9;
            }else{
                price=price};

            if (price>="300") {
                newprice=price;
            }else{
                newprice=price+20;}
            TotalBox.value="Your total is $" + newprice;
        }
    </script>
</head>
<body>
<form name="aaa">
Toaster: <input name="ToasterBox" type="text" size=15>
<br>

sandwich maker: <input name="SandwichBox" type="text" size=15>
<br>

Total : <input name="TotalBox" type=text size=30>
<input type="button" value="Click Here"
       onClick="total(ToasterBox.value,SandwichBox.value)">
</form>
</body>
</html>