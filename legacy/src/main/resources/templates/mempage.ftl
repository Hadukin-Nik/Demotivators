<!DOCTYPE html>
<html>
<#include "common.ftl">
<#include "menu.ftl">
<body>


<div class="img-container">
    <img src="/app/${mem.path}" alt="your_mem">
</div>
<hr class="mt-2 mb-3"/>

<#if isNotFriend>
    <a href="/app/createRequest/${mem.userId}">addToFriend</a>
    <br/>
</#if>
<hr class="mt-2 mb-3"/>


<a>${mem.description}</a>
<hr class="mt-2 mb-3"/>

<a>${mem.tags}</a>
<hr class="mt-2 mb-3"/>

<#foreach item in comments>
    <div class="row">
        <div class="col">
            <a class="author">${item.user.name}</a>
        </div>
    </div>
    <div class = "row">
        <div class="col">
            <a>${item.text}</a>
        </div>
    </div>
    <hr class="mt-2 mb-3"/>

</#foreach>


<form method="POST" class = "comment">
    <div class="row">
        <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label">Your comment</label>
            <textarea name="comment" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>
