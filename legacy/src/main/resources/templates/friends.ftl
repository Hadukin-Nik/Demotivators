<#include "common.ftl">
<#include "menu.ftl">

<#foreach item in friends>
    <tr class="RequestsWithUser">
        <div class="row">
            <div class="img-container">
                <img src="${item.sender.avatar}" alt="sender">
                <a>${item.text}</a>
                <br />
                <form method="post" action="/app/requests/${item.requestId}/reject">
                    <button type="submit">
                        Reject
                    </button>
                </form>
            </div>
        </div>
    </tr>

    <a href = "/app/menu"> Back</a>
</#foreach>