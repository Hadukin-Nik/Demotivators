<#include "common.ftl">
<#include "menu.ftl">

<style>
    table, th, td {
        border:1px solid black;
    }
</style>

<form method="POST">
    <div class = "row comment">
        <a>Name</a>
        <textarea name="name" rows="4" cols="50"> </textarea>
    </div>

    <div class="row comment">
        <a>Description</a>
        <textarea name="description" rows="4" cols="50"> </textarea>
    </div>

    <div class="row comment">
        <div class="col">
            <label class="form-check-label" for="exampleCheck1">is it private?</label>
            <input type="checkbox" class="form-check-input" id="exampleCheck1"  name="isPrivate">
        </div>
        <div class="col">
            <input type="submit" value="Upload" />
        </div>
    </div>

</form>

<table>
    <tr class="Collection">
        <th>Name</th>
        <th>Description</th>
        <th>Collection_id</th>
        <th>Creator_id</th>
    </tr>
    <#foreach item in collections>
        <tr class="Collection">
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.id} </td>
            <td>${item.creatorId} </td>
        </tr>
    </#foreach>
</table>