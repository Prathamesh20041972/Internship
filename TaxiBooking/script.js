let taxis=[];
let bookingId=1;

for(let i=1;i<=4;i++){
taxis.push({
id:i,
location:'A',
freeTime:0,
earnings:0,
km:0,
trips:[]
});
}

function pos(p){
return p.charCodeAt(0)-65;
}

function distance(a,b){
return Math.abs(pos(a)-pos(b))*15;
}

function fare(a,b){
let d=distance(a,b);
if(d<=5) return 100;
return 100+(d-5)*10;
}

function bookTaxi(){

let cid=document.getElementById("cid").value;
let pickup=document.getElementById("pickup").value;
let drop=document.getElementById("drop").value;
let time=parseInt(document.getElementById("time").value);

if(!cid||!time){ alert("Enter all fields"); return; }
if(pickup===drop){ alert("Pickup and Drop same"); return; }

let available=[];

for(let t of taxis){

let reach=Math.abs(pos(t.location)-pos(pickup));

if(t.freeTime+reach<=time){
available.push(t);
}
}

if(available.length===0){
document.getElementById("result").innerHTML="❌ No Taxi Available";
return;
}

available.sort((a,b)=>{

let d1=Math.abs(pos(a.location)-pos(pickup));
let d2=Math.abs(pos(b.location)-pos(pickup));

if(d1!==d2) return d1-d2;

return a.earnings-b.earnings;
});

let taxi=available[0];

let trip=Math.abs(pos(pickup)-pos(drop));
let dropTime=time+trip;

let money=fare(pickup,drop);
let km=distance(pickup,drop);

taxi.location=drop;
taxi.freeTime=dropTime;
taxi.earnings+=money;
taxi.km+=km;

taxi.trips.push({
bid:bookingId++,
cid,pickup,drop,time,dropTime,money,km
});

document.getElementById("result").innerHTML=
"✅ Taxi "+taxi.id+" booked successfully";

document.getElementById("cid").value="";
document.getElementById("time").value="";
}

function showDetails(){

let html="";

for(let t of taxis){

html+=`<h3>Taxi-${t.id} | Earnings ₹${t.earnings} | KM ${t.km}</h3>`;

if(t.trips.length>0){

html+=`
<table>
<tr>
<th>Booking</th><th>Cust</th><th>From</th><th>To</th>
<th>Pickup</th><th>Drop</th><th>KM</th><th>Fare</th>
</tr>
`;

for(let tr of t.trips){

html+=`
<tr>
<td>${tr.bid}</td>
<td>${tr.cid}</td>
<td>${tr.pickup}</td>
<td>${tr.drop}</td>
<td>${tr.time}</td>
<td>${tr.dropTime}</td>
<td>${tr.km}</td>
<td>${tr.money}</td>
</tr>`;
}

html+="</table>";
}

html+="<br>";
}

document.getElementById("details").innerHTML=html;
}
