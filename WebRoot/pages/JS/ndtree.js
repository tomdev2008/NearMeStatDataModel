/*--------------------------------------------------|
| dTree 2.05 | www.destroydrop.com/javascript/tree/ |
|---------------------------------------------------|
| Copyright (c) 2002-2003 Geir Landr�               |
|                                                   |
| This script can be used freely as long as all     |
| copyright messages are intact.                    |
|                                                   |
| Updated: 17.04.2003                               |
|--------------------------------------------------*/
// Node object
function Node(id, pid, name, url, target, open) {
	this.id = id;
	this.pid = pid;//父节点
	this.name = name;//名称
	this.url = url;//路径
	this.target = target;
	this._io = open || false;
	this._is = false;//是否是选中节点
	this._ls = false;//是否是节点下最后一个节点
	this._hc = false;//是否有子节点
	this._ai = 0;
	this._p;
};
// Tree object
function dTree(objName) {
	this.config = {
		target : null,
		closeSameLevel : false
	}
	this.obj = objName;
	this.aNodes = [];
	this.aIndent = [];
	this.root = new Node(-1);
	this.selectedNode = null;
	this.selectedFound = false;
	this.completed = false;
};
// Adds a new node to the node array
dTree.prototype.add = function(id, pid, name, url, target, open) {
	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, target, open);
};
// Open/close all nodes
dTree.prototype.openAll = function() {
	this.oAll(true);
};
dTree.prototype.closeAll = function() {
	this.oAll(false);
};

// Outputs the tree to the page
dTree.prototype.toString = function() {
	var str = '';
	if (document.getElementById) {
		str += this.addNode(this.root);
	} else
		str += 'Browser not supported.';
	if (!this.selectedFound)
		this.selectedNode = null;
	this.completed = true;
	return str;
};

// Creates the tree structure
dTree.prototype.addNode = function(pNode) {
	var str = '';
	var n = 0;
	for (n; n < this.aNodes.length; n++) {
		if (this.aNodes[n].pid == pNode.id) {
			var cn = this.aNodes[n];
			cn._p = pNode;
			cn._ai = n;
			this.setCS(cn);
			if (!cn.target && this.config.target)
				cn.target = this.config.target;
			if (cn.id == this.selectedNode && !this.selectedFound) {
				cn._is = true;
				this.selectedNode = n;
				this.selectedFound = true;
			}
			str += this.node(cn, n);
			if (cn._ls)
				break;
		}
	}
	return str;
};

// Creates the node, url and text
dTree.prototype.node = function(node, nodeId) {
	var str = '';
	
	if(node.id == '0'){//根节点
//		str += '<li>' 
//			+ node.name
//			+ '</li>';
	} else if(node.url) {//叶子节点
		str += '<div class="leaf">' 
			+ '<a id="s'
		    + this.obj
	        + nodeId + '"' 
	        + ' class="' + ((node._is ? 'nodeSel' : 'node')) + '"'
	        + ' href="' + node.url + '"'
		    + ' target="' + node.target + '"'
		    + ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');">'
		    + node.name 
		    + '</a>' 
		    + '</div>';
	} else {//目录项节点
		str += '<div class="catalog">' 
			+ '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">'
			+ node.name
			+ '</a>'
			+ '</div>';
	}
	
	//目录项包含叶子节点，控制隐藏和显示
	if (node._hc) {
		str += '<div id="d' + this.obj + nodeId
				+ '" class="clip" style="display:'
				+ ((this.root.id == node.pid || node._io) ? 'block' : 'none')
				+ ';">';
		str += this.addNode(node);
		str += '</div>';
	}
	this.aIndent.pop();
	return str;
};

// Checks if a node has any children and if it is the last sibling
dTree.prototype.setCS = function(node) {
	var lastId;
	for ( var n = 0; n < this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.id) {
			node._hc = true;
		}
		if (this.aNodes[n].pid == node.pid) {
			lastId = this.aNodes[n].id;
		}
	}
	if (lastId == node.id)
		node._ls = true;
};

// Returns the selected node
dTree.prototype.getSelected = function() {
	var sn = this.getCookie('cs' + this.obj);
	return (sn) ? sn : null;
};

// Highlights the selected node
dTree.prototype.s = function(id) {
	var cn = this.aNodes[id];
	if (cn._hc)
		return;
	if (this.selectedNode != id) {
		if (this.selectedNode || this.selectedNode == 0) {
			eOld = document.getElementById("s" + this.obj + this.selectedNode);
			eOld.className = "node";
		}
		eNew = document.getElementById("s" + this.obj + id);
		eNew.className = "nodeSel";
		this.selectedNode = id;
	}
};

// Toggle Open or close
dTree.prototype.o = function(id) {
	var cn = this.aNodes[id];
	this.nodeStatus(!cn._io, id, cn._ls);
	cn._io = !cn._io;
	if (this.config.closeSameLevel)
		this.closeLevel(cn);
};

// Open or close all nodes
dTree.prototype.oAll = function(status) {
	for ( var n = 0; n < this.aNodes.length; n++) {
		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {
			this.nodeStatus(status, n, this.aNodes[n]._ls)
			this.aNodes[n]._io = status;
		}
	}
};

// Opens the tree to a specific node
dTree.prototype.openTo = function(nId, bSelect, bFirst) {
	if (!bFirst) {
		for ( var n = 0; n < this.aNodes.length; n++) {
			if (this.aNodes[n].id == nId) {
				nId = n;
				break;
			}
		}
	}
	var cn = this.aNodes[nId];
	if (cn.pid == this.root.id || !cn._p)
		return;
	cn._io = true;
	cn._is = bSelect;
	if (this.completed && cn._hc)
		this.nodeStatus(true, cn._ai, cn._ls);
	if (this.completed && bSelect)
		this.s(cn._ai);
	else if (bSelect)
		this._sn = cn._ai;
	this.openTo(cn._p._ai, false, true);
};

// Closes all nodes on the same level as certain node
dTree.prototype.closeLevel = function(node) {
	for ( var n = 0; n < this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id
				&& this.aNodes[n]._hc) {
			this.nodeStatus(false, n, this.aNodes[n]._ls);
			this.aNodes[n]._io = false;
			this.closeAllChildren(this.aNodes[n]);
		}
	}
}

// Closes all children of a node
dTree.prototype.closeAllChildren = function(node) {
	for ( var n = 0; n < this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {
			if (this.aNodes[n]._io)
				this.nodeStatus(false, n, this.aNodes[n]._ls);
			this.aNodes[n]._io = false;
			this.closeAllChildren(this.aNodes[n]);
		}
	}
}
// Open all children of a node
dTree.prototype.openAllChildren = function(nId) {
	this.openTo(nId);
	for ( var n = 0; n < this.aNodes.length; n++) {
		if (this.aNodes[n].pid == nId && this.aNodes[n]._hc) {
			this.openAllChildren(this.aNodes[n].id);
		}
	}
}
dTree.prototype.findFirstUrl = function(nId) {
	for ( var n = 0; n < this.aNodes.length; n++) {
		if (this.aNodes[n].pid == nId) {
			if (this.aNodes[n].url != '' && this.aNodes[n].url != null) {
				return n;
			} else if (this.aNodes[n]._hc) {
				u = this.findFirstUrl(this.aNodes[n].id);
				if (u != '') {
					return u;
				}
			}
		}
	}
	return -1;
}

// Change the status of a node(open or closed)
dTree.prototype.nodeStatus = function(status, id, bottom) {
	eDiv	= document.getElementById('d' + this.obj + id);
	eDiv.style.display = (status) ? 'block': 'none';
};


// [Cookie] Clears a cookie
dTree.prototype.clearCookie = function() {
	var now = new Date();
	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
	this.setCookie('co'+this.obj, 'cookieValue', yesterday);
	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);
};

// [Cookie] Sets value in a cookie
dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {
	document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};

// [Cookie] Gets a value from a cookie
dTree.prototype.getCookie = function(cookieName) {
	var cookieValue = '';
	var posName = document.cookie.indexOf(escape(cookieName) + '=');
	if (posName != -1) {
		var posValue = posName + (escape(cookieName) + '=').length;
		var endPos = document.cookie.indexOf(';', posValue);
		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));
		else cookieValue = unescape(document.cookie.substring(posValue));
	}
	return (cookieValue);
};

// [Cookie] Returns ids of open nodes as a string
dTree.prototype.updateCookie = function() {
	var str = '';
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {
			if (str) str += '.';
			str += this.aNodes[n].id;
		}
	}
	this.setCookie('co' + this.obj, str);
};

// [Cookie] Checks if a node id is in a cookie
dTree.prototype.isOpen = function(id) {
	var aOpen = this.getCookie('co' + this.obj).split('.');
	for (var n=0; n<aOpen.length; n++)
		if (aOpen[n] == id) return true;
	return false;
};

// If Push and pop is not implemented by the browser
if (!Array.prototype.push) {
	Array.prototype.push = function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
};
if (!Array.prototype.pop) {
	Array.prototype.pop = function array_pop() {
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
};