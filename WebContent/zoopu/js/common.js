/**
 * ��ѡ��ȫѡ����
 * 
 * @param obj	--	ȫѡԪ��
 * @param name 	--	��ѡԪ��name
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
 * ����������ѡ�����
 * 
 * @param str	--	��ʾ��Ϣ
 * @param name	--	ѡ��Ԫ�ص�name 
 * @param url	--	��תURL
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