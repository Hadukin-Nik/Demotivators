<#include "common.ftl">
<#include "menu.ftl">

<style>
    table, th, td {
        border:1px solid black;
    }
</style>
<table>
    <tr class="User">
        <th>Name</th>
        <th>NickName</th>
        <th>Date</th>

    </tr>
    <#foreach item in users>
        <tr class="User">
            <td>${item.name}</td>
            <td>${item.nickname}</td>
            <#setting date_format="dd-MM-yyyy">
            <#assign createdOn='${item.date_registration?date}'>

            <td>${createdOn} </td>
        </tr>
    </#foreach>
</table>