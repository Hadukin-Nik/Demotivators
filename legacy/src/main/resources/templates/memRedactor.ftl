
<!DOCTYPE html>
<html>
<#include "common.ftl">
<#include "menu.ftl">

<div class="img-container">
    <img src="/app/images/${mem.picture}" alt="your_mem">
</div>

<form method="Post" action="">
    <div class="row">
        <#if mem.commentsAllowed>
            <input type="checkbox" name="isCommentsAllowed" checked>
        <#else>
            <input type="checkbox" name="isCommentsAllowed">
        </#if>
        <label for="isCommentsAllowed"> Are comments allowed?</label><br>

        <input type="checkbox" name="deleteMem">
        <label for="deleteMem">Delete mem?</label><br>
    </div>

    <div class="mb-3">
        <label for="exampleFormControlTextarea1" class="form-label">Description</label>
        <textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="3">${mem.description}</textarea>
    </div>
    <div class="mb-3">
        <label for="exampleFormControlTextarea1" class="form-label">Tags</label>
        <textarea name="tags" class="form-control" id="exampleFormControlTextarea1" rows="3">${mem.tags} </textarea>
    </div>

    <input type="submit" value="Upload"/>
</form>

<a href="">To mem page</a>

