$(window).on("load",function(){
    $(".loading").css("display","none");

$('.logo').click(function(){
    $(this).siblings('input').attr('type','text');
    $(this).css('display','none');
    $(this).siblings('.offLogo').css('display','block');
});
$('.offLogo').click(function(){
    $(this).siblings('input').attr('type','password');
    $(this).css('display','none');
    $(this).siblings('.logo').css('display','block');
});

var msg=$(".msgCont");
var msgCloseBtn=$(".msgCloseBtn");
if(msg.html())
{
    $(".msgBase").css("display","block");
}
else
{
    $(".msgBase").css("display","none");
}

msgCloseBtn.on("click",function(){
    $(".msgBase").css("display","none");
});

// $(".registerForm").on("submit",function(){
//     alert("form Submitted");
// });

});

