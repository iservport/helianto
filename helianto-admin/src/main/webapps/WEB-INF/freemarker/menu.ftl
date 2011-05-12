<div class="menu">
<ul>
<li><@secure "ROLE_ADMIN_MANAGER" ><@anchor "toAdmin" >ADMIN</@anchor></@secure>
<ul>
<li><@secure "ROLE_ADMIN_MANAGER" ><@anchor "toUsers" >Users</@anchor></@secure></li>
</ul>
</li>
<li><@anchor "logout" >Sair</@anchor></li>
</ul>
</div>
<div style="float:right;">${currentUser.principal.user.userKey}</div>
    