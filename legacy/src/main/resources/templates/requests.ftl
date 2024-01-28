<#include "common.ftl">
<#include "menu.ftl">

<#foreach item in requests>
    <div class="RequestsWithUser">
        <div class="img-container">
            <img src="${item.sender.avatar}" alt="sender">
            <a>${item.text}</a>
            <br/>
                <button type="submit" class="approve-request">
                    Approve
                </button>
            <br/>
                <button type="submit" class="reject-request">
                    Reject
                </button>
            <hr class="mt-2 mb-3"/>

        </div>
    </div>
</#foreach>

<a href="/app/menu"> Back</a>
