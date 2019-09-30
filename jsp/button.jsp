 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="buttons-main">
		<div class="btn-effcts panel-widget">				
			<a href="/attendance_management" class="hvr-push">Attendance Management</a>
			<a href="/fee_management" class="hvr-pop">Fee Management</a>	
			<a href="/student_management" class="hvr-pop">Students Management</a>
			<a href="/batch_management" class="hvr-pop">Batch Management</a>
			<c:choose>
		  	<c:when test="${userrole == 'admin'}">
		  		<a href="/batch_management" class="hvr-pop">new Management</a>
		   	</c:when>
		   </c:choose>
			
			
			
						
		</div>	
	</div> 