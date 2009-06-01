<#assign base="user" />
<#assign title="users" />

<#assign userStateFilter={
     ' ' : "All"
   , 'A' : "Active"
   , 'I' : "Inactive"
} />
<#assign userState={
     'A' : "Active"
   , 'I' : "Inactive"
} />
<#assign gender={
     'M' : "Male"
   , 'F' : "Female"
   , 'N' : "Not supplied"
} />
<#assign appellation={
     '0' : "Any"
   , '2' : "Formal"
} />
<#assign identityType={
     'N' : "Not valid e-mail"
   , 'O' : "Organizational e-mail"
   , 'P' : "Personal e-mail"
} />
<#assign notification={
     'A' : "Automatic"
   , 'R' : "Upon request"
} />


