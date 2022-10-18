<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${pagination.prev}">
                <li class="page-item"><a href="javascript:void(0);" class="page-link" onclick="fn_go_page(${pagination.nowPage - 1})">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled"><a href="javascript:void(0);" class="page-link" onclick="fn_go_page(${pagination.nowPage - 1})" >Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach var="pno" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
            <li class="page-item <c:if test="${pno == searchDto.nowPage}">active</c:if>"><a href="javascript:void(0);" class="page-link" onclick="fn_go_page(${pno})">${pno}</a></li>
        </c:forEach>

        <c:choose>
            <c:when test="${pagination.next}">
                <li class="page-item"><a href="javascript:void(0);" class="page-link" onclick="fn_go_page(${pagination.nowPage + 1})">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled"><a href="javascript:void(0);" class="page-link" onclick="fn_go_page(${pagination.nowPage + 1})">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>

<script type="text/javascript">

    function fn_go_page(nowPage) {

        let obj;
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', 'nowPage');
        obj.setAttribute('value', nowPage);

        let form = document.getElementById('searchForm');
        form.appendChild(obj);

        form.submit();
    }
</script>

