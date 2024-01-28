<!DOCTYPE html>
<html>
<#include "common.ftl">
<#include "menu.ftl">

<body>

<#foreach item in memes>
    <tr class="MemWithUser">
        <div class="img-container">
            <img src="${item.path}" alt="your_mem">
            <img src="${item.user.avatar}" alt="creator">

            <div class="img-text">
                <a href="/app/memes/${item.memId}/comments"> ${item.description}</a>
            </div>

            <#if userId == item.userId || isAdmin>
                <div class="img-button">
                    <a href="/app/memes/${item.memId}/edit">Edit</a>
                </div>
            </#if>
        </div>
    </tr>
</#foreach>

</body>
</html>