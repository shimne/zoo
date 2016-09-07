/**
 * 复选框全选方法
 * 
 * @param obj	--	全选元素
 * @param name 	--	被选元素name
 */
function checkAll(obj, name)
{
	var checkObjs = document.getElementsByName(name);

	for (var i = 0; i < checkObjs.length; i++)
	{
		checkObjs[i].checked = obj.checked;
	}
}

/**
 * 操作所有以选择对象
 * 
 * @param str	--	提示信息
 * @param name	--	选择元素的name 
 * @param url	--	跳转URL
 */
function optionAll(str, name, url)
{
	var checks = document.getElementsByName(name);

	var ids = '';
	for (var i = 0; i < checks.length; i++)
	{
		if (checks[i].checked)
		{
			ids += (checks[i].value + ',');
		}
	}

	if (ids != '')
	{
		ids = ids.substr(0, ids.length-1);

		url += ids;

		confirm(str)
		{
			this.location = url;
		}
	}
}