<div class="menu">
<ul>
<li><a href="_welcome">Welcome</a></li>
<ul>
<li><@secure "ROLE_USER_ALL" ><@anchor "toAdmin" >ADMIN</@anchor></@secure>
<ul>
<li><@secure "ROLE_ADMIN_MANAGER" ><@anchor "toUsers" >Users</@anchor></@secure></li>
</ul>
</li>
<li><@secure "ROLE_USER_ALL" ><@anchor "logout" >Sair</@anchor></@secure></li>
</ul>
</div>
<@secure "ROLE_USER_ALL" >
<div style="float:right;">${currentUser.principal.user.userKey}</div>
</@secure>