angular.module('jakPoliczycDirectives')
    .directive('jpMenuTree', ['$compile', '$rootScope', 'modalService', 'jpTemplateService', function ($compile, $rootScope, modalService, jpTemplateService) {
        return {
            restrict: 'E',
            scope: {
                model: '='
            },
            link: function (scope, element) {

                var source,
                    drawId = "draw-id";

                if (angular.isDefined(scope.model)) {
                    drawTop();
                    drawMenu(scope.model, 0);
                }

                function drawTop() {
                    element.append("<div></div>");

                    var newEl = angular.element("<div>Top</div>");
                    newEl[0].setAttribute('class', 'tree-top');
                    newEl[0].setAttribute(drawId, 'm0');
                    newEl[0].setAttribute('style', 'margin-left: 0');
                    newEl[0].setAttribute('ondragover', angularScope('allowDropOnlyMenu(event)'));
                    newEl[0].setAttribute('ondrop', angularScope('move(event)'));

                    element.children().append(newEl);
                    $compile(newEl)(scope);
                }

                function drawMenu(menus, space) {
                    menus.forEach(function (menu) {
                        var menuDrawId = 'm' + menu.id;
                        append(menu.name, menuDrawId, space);

                        var articles = menu.articles;
                        articles.forEach(function (article) {
                            var articleDrawId = "a" + article.id;
                            append(article.story.title, articleDrawId, space, false);
                        });

                        var submenus = menu.submenus;
                        drawMenu(submenus, space + 1);
                    });
                }

                function getRemoveButtonElement(menuId) {
                    var newEl = angular.element("<button>X</button>");
                    newEl[0].setAttribute('class', 'tree-remove');
                    newEl[0].setAttribute(drawId, 'm' + menuId);
                    newEl[0].setAttribute('onclick', angularScope('removeClick(event)'));
                    return newEl;
                }

                scope.dbClick = function (event) {
                    var menu = getMenu(scope.model, getId(event.srcElement));
                    modalService.inputExecute(function (input, menu) {
                        menu.name = input;
                        redraw();
                    }, $rootScope.language.editMenu, menu.name, menu);
                };

                function angularScope(fnName) {
                    return "angular.element(this).scope()." + fnName;
                }

                function append(txt, _drawId, deepLevel, _menu) {
                    var droppable = angular.isUndefined(_menu) ? true : _menu;
                    var cssClass = droppable ? 'tree-menu' : 'tree-article';
                    var margin = (droppable ? deepLevel * 10 : (deepLevel * 10 + 10)) + "px";

                    var newEl = angular.element("<div>" + txt + "</div>");
                    newEl[0].setAttribute('class', cssClass);
                    newEl[0].setAttribute('draggable', true);
                    newEl[0].setAttribute('style', 'margin-left:' + margin);
                    newEl[0].setAttribute(drawId, _drawId);
                    newEl[0].setAttribute('ondragstart', angularScope('onDragStart(event)'));
                    newEl[0].setAttribute('ondrop', angularScope('move(event)'));

                    if (droppable) {
                        newEl[0].setAttribute('ondragover', angularScope('allowDrop(event)'));
                        newEl[0].setAttribute('ondblclick', angularScope('dbClick(event)'));
                    }

                    if (isMenu(newEl)) {
                        var menu = getMenu(scope.model, getId(newEl));
                        if (menu.articles.length === 0 && menu.submenus.length === 0) {
                            var button = getRemoveButtonElement(getId(newEl));
                            newEl.append(button);
                        }
                    }
                    element.children().append(newEl);

                    $compile(newEl)(scope);
                }

                scope.allowDropOnlyMenu = function (event) {
                    if (isMenu(source)) {
                        event.preventDefault();
                    }
                };

                scope.allowDrop = function (event) {
                    var target = event.srcElement;
                    if (getDrawId(source) !== getDrawId(target)) {
                        if (!isMenu(source) || !isChild(getId(source), getId(target))) {
                            event.preventDefault();
                        }
                    }
                };

                scope.removeClick = function (event) {
                    modalService.execute(function () {
                        removeMenu(undefined, scope.model, getId(event.srcElement), undefined, false);
                        redraw();
                    }, $rootScope.language.msgRemove);
                };

                scope.onDragStart = function (event) {
                    source = event.srcElement;
                };

                scope.move = function (event) {
                    var target = event.target;
                    var getName = function (elem) {
                        var menu = getMenu(scope.model, getId(elem));
                        return (menu && menu.name) || 'Top';
                    };
                    var msg = isMenu(source) ? jpTemplateService($rootScope.language.msgMenuMove)(getName(source), getName(target))
                        : jpTemplateService($rootScope.language.msgArtMove)(getName(target));
                    modalService.execute(function () {
                        move(source, target);
                    }, msg);
                };

                function move(source, target) {
                    var args = [getId(source), getId(target)];
                    isMenu(source) ? moveMenu.apply(this, args) : moveArticle.apply(this, args);
                    redraw();
                }

                function moveMenu(sourceId, targetId) {
                    removeMenu(undefined, scope.model, sourceId, targetId, true);
                }

                function pasteMenu(menus, targetId, _menu) {
                    if (targetId === 0) {
                        scope.model.push(_menu);
                        return;
                    }

                    menus.forEach(function (menu) {
                        if (menu.id === targetId) {
                            menu.submenus.push(_menu);
                            return;
                        }
                        pasteMenu(menu.submenus, targetId, _menu);
                    });
                }

                function removeMenu(parent, menus, sourceId, targetId, shouldPast) {
                    menus.forEach(function (menu) {
                        if (menu.id === sourceId) {
                            var newMenu = _.without(menus, menu);
                            if (angular.isDefined(parent)) {
                                parent.submenus = newMenu;
                            } else {
                                scope.model = newMenu;
                            }
                            if (shouldPast) {
                                pasteMenu(scope.model, targetId, menu);
                            }
                        }
                        removeMenu(menu, menu.submenus, sourceId, targetId, shouldPast);
                    });
                }

                function moveArticle(sourceId, targetId) {

                    var pasteArticle = function (menus, menuId, _article) {
                        menus.forEach(function (menu) {
                            if (menu.id === menuId) {
                                menu.articles.push(_article);
                                return;
                            }
                            pasteArticle(menu.submenus, menuId, _article);
                        });
                    };

                    var removeArticle = function (menus, menuId, articleId) {
                        menus.forEach(function (menu) {
                            var articles = menu.articles;
                            articles.forEach(function (article) {
                                if (article.id === articleId) {
                                    menu.articles = _.without(articles, article);
                                    pasteArticle(scope.model, menuId, article);
                                }
                            });
                            removeArticle(menu.submenus, menuId, articleId);
                        });
                    };

                    removeArticle(scope.model, targetId, sourceId);
                }

                function isMenu(elem) {
                    return getDrawId(elem).charAt(0) === 'm';
                }

                function getId(elem) {
                    return parseInt(getDrawId(elem).substring(1));
                }

                function getDrawId(elem) {
                    return angular.element(elem).attr(drawId);
                }

                function isChild(sourceId, targetId) {

                    var foundChild;
                    var foundMenu = getMenu(scope.model, sourceId);

                    var child = function (menu, childId) {
                        var submenus = menu.submenus;
                        if (submenus.length === 0) {
                            return false;
                        }
                        for (var i = 0; i < submenus.length; i++) {
                            if (submenus[i].id === childId) {
                                foundChild = true;
                            }
                            child(submenus[i], childId);
                        }
                    };

                    child(foundMenu, targetId);
                    return foundChild;
                }

                function getMenu(menus, menuId) {
                    var foundMenu;
                    var findMenu = function (menus, menuId) {
                        for (var i = 0; i < menus.length; i++) {
                            var menu = menus[i];
                            if (menu.id === menuId) {
                                foundMenu = menu;
                            }
                            findMenu(menu.submenus, menuId);
                        }
                    };
                    findMenu(menus, menuId);
                    return foundMenu;
                }

                function redraw() {
                    element.children().remove();
                    drawTop();
                    drawMenu(scope.model, 0);
                }

            }
        }
    }
    ]);