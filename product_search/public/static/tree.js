function Node(data, payload) {
    this.data = data;
    this.payload = payload;
    this.parent = null;
    this.children = [];
}

function Tree(data, playload) {
    var node = new Node(data, playload);
    this._root = node;
}

Tree.prototype.traverseDF = function (callback) {

    // this is a recurse and immediately-invoking function
    (function recurse(currentNode) {
        // step 2
        for (var i = 0, length = currentNode.children.length; i < length; i++) {
            // step 3
            recurse(currentNode.children[i]);
        }

        // step 4
        callback(currentNode);

        // step 1
    })(this._root);

};

Tree.prototype.traverseBF = function (callback) {
    var queue = new Queue();

    queue.enqueue(this._root);

    currentTree = queue.dequeue();

    while (currentTree) {
        for (var i = 0, length = currentTree.children.length; i < length; i++) {
            queue.enqueue(currentTree.children[i]);
        }

        callback(currentTree);
        currentTree = queue.dequeue();
    }
};

 
/*
function _updateData (node, data) {
    console.log(getSpace(space) ,node.data);                                
    data
    node.children.forEach(function(child) {
        _updateData(child, data);
    });
};


Tree.prototype.updateData = function (data) {
    _updateData(this._root, data);    
};
*/

Tree.prototype.contains = function (callback, traversal) {
    traversal.call(this, callback);
};

Tree.prototype.add = function (data, toData, traversal) {
    var child = new Node(data),
        parent = null,
        callback = function (node) {
            if (node.data === toData) {
                parent = node;
            }
        };

    this.contains(callback, traversal);

    if (parent) {
        parent.children.push(child);
        child.parent = parent;
    } else {
        throw new Error('Cannot add node to a non-existent parent.');
    }
};

Tree.prototype.remove = function (data, fromData, traversal) {
    var tree = this,
        parent = null,
        childToRemove = null,
        index;

    var callback = function (node) {
        if (node.data === fromData) {
            parent = node;
        }
    };

    this.contains(callback, traversal);

    if (parent) {
        index = findIndex(parent.children, data);

        if (index === undefined) {
            throw new Error('Node to remove does not exist.');
        } else {
            childToRemove = parent.children.splice(index, 1);
        }
    } else {
        throw new Error('Parent does not exist.');
    }

    return childToRemove;
};
Tree.prototype.findNode = function (name) {
    var tree = this,
        parent = null,
        childToRemove = null,
        index;        
    var foundNode = null;
    tree.traverseDF(function (node) {           
        if(node.data === name){                
            foundNode = node;
            return foundNode;
        }
    });
    return foundNode;
}
Tree.prototype.print = function () {         
    _print(this._root, 0);
}

function _print (node, space) {
    console.log(getSpace(space) ,node.data);                                
    node.children.forEach(function(child) {
        _print(child, space+1);
    });
};


function _visitTree (node, callback) {
    callback(node);    
    node.children.forEach(function(child) {
        _visitTree(child, callback);
    });
};

Tree.prototype.visitTree = function (callback) {
    _visitTree(this._root, callback);    
}


Tree.prototype.addPath = function (paths, doc_count) {    
    console.log("paths:"+paths+" doc_count:"+doc_count);
    var tree = this;    
    if(paths.length === 1) {
        var root = tree.findNode(paths[0]);                
        if(root === null) {
            var ret = new Node(paths[0], doc_count);            
            tree._root.children.push(ret);
            return ret;
        }else {
            if(doc_count > 0){ 
                root.payload = doc_count;
            }            
            return root;
        }
        
    }else {
        if(paths.length > 1) {                
            var root = tree.findNode(paths[0]);            
            if(root === null){ 
                var child = new Node(paths[0], doc_count);                
                var parentNode = this.addPath(paths.splice(1, paths.length), -1);
                parentNode.children.push(child);
                return child;
            }else {
                if(doc_count > 0){ 
                    root.payload = doc_count;
                }            
                //root.payload = doc_count;
                return root;
            }               
            
        }        
    }
    
};

function getSpace(spaceCount){
    var str = "";
    for (var i = 0; i < spaceCount; i++) {
        str = str + "-";
    }
    return str;
}
function findIndex(arr, data) {
    var index;

    for (var i = 0; i < arr.length; i++) {
        if (arr[i].data === data) {
            index = i;
        }
    }

    return index;
}