angular.module('jakPoliczycApp').run(['$templateCache', function($templateCache) {
  'use strict';

  $templateCache.put('views/add-article.html',
    "<h2>{{language.artAdd}}</h2>\r" +
    "\n" +
    "\r" +
    "\n" +
    "<jpadd></jpadd>"
  );


  $templateCache.put('views/article.html',
    "<jparticle ng-if=\"ready\" article=\"{{article}}\" add=\"addComment(author, content)\"></jparticle>"
  );


  $templateCache.put('views/articles-carousel.html',
    "<div>\r" +
    "\n" +
    "    <div uib-carousel active=\"0\" interval=\"5000\" no-wrap=\"false\">\r" +
    "\n" +
    "        <div uib-slide ng-repeat=\"article in filteredArticles track by article.id\" ng-if=\"$index < 3\" index=\"$index\">\r" +
    "\n" +
    "            <h4>{{article.title}}</h4>\r" +
    "\n" +
    "            <p>{{article.title}}</p>\r" +
    "\n" +
    "            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum libero nibh, et tincidunt lacus malesuada eget. Duis lacus lacus, luctus quis tellus at, viverra condimentum sem. Sed non tellus dolor. Maecenas volutpat scelerisque augue, ac scelerisque eros convallis vitae. Donec volutpat lorem quis commodo fringilla. Cras pretium eu nisl non pellentesque. Cras vehicula nibh diam, vel pharetra lorem eleifend sed. Suspendisse egestas placerat urna, eu tristique arcu consequat sit amet. Sed rutrum sed leo sed condimentum. Proin ultricies nisl arcu, quis blandit nunc gravida a. Phasellus posuere vestibulum aliquet. Donec nec elit id velit tincidunt pharetra nec in dolor. In pretium non risus at lacinia.</div>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "</div>"
  );


  $templateCache.put('views/articles.html',
    "<div class=\"jplist\">\r" +
    "\n" +
    "    <h2 ng-hide=\"isFilter\">{{language.artTitle}}</h2>\r" +
    "\n" +
    "    <h2 ng-show=\"isFilter\">{{language.filterRes}}</h2>\r" +
    "\n" +
    "    <h3 ng-show=\"isFilter && chosenCategory\">{{language.category}}: {{chosenCategory}}</h3>\r" +
    "\n" +
    "    <h3 ng-show=\"isFilter && chosenTags\">{{language.tags}}: {{chosenTags}}</h3>\r" +
    "\n" +
    "    <div ng-if=\"filteredArticles && filteredArticles.length >= 3 && !isFilter\"\r" +
    "\n" +
    "         class=\"hide-s\" ng-include=\"'views/articles-carousel.html'\"></div>\r" +
    "\n" +
    "    <table>\r" +
    "\n" +
    "        <tr>\r" +
    "\n" +
    "            <th>{{language.no}}</th>\r" +
    "\n" +
    "            <th style=\"cursor: pointer;\" ng-click=\"orderBy(header.names.KIND)\"><i uib-tooltip=\"Sortuj\" class='fa' ng-class=\"{'jpselected': header.values[header.names.KIND].selected || header.values[header.names.KIND].reversed, 'fa-caret-square-o-down': !header.values[header.names.KIND].reversed, 'fa-caret-square-o-up': header.values[header.names.KIND].reversed}\" aria-hidden='true'></i> {{language.kind}}</th>\r" +
    "\n" +
    "            <th style=\"cursor: pointer;\" ng-click=\"orderBy(header.names.TITLE)\"><i uib-tooltip=\"Sortuj\" class='fa' ng-class=\"{'jpselected': header.values[header.names.TITLE].selected || header.values[header.names.TITLE].reversed, 'fa-caret-square-o-down': !header.values[header.names.TITLE].reversed, 'fa-caret-square-o-up': header.values[header.names.TITLE].reversed}\" aria-hidden='true'></i> {{language.title}}</th>\r" +
    "\n" +
    "            <th>{{language.tags}}</th>\r" +
    "\n" +
    "            <th style=\"cursor: pointer;\" ng-click=\"orderBy(header.names.DATE)\"><i uib-tooltip=\"Sortuj\" class='fa' ng-class=\"{'jpselected': header.values[header.names.DATE].selected || header.values[header.names.DATE].reversed, 'fa-caret-square-o-down': !header.values[header.names.DATE].reversed, 'fa-caret-square-o-up': header.values[header.names.DATE].reversed}\" aria-hidden='true'></i> {{language.date}}</th>\r" +
    "\n" +
    "        </tr>\r" +
    "\n" +
    "        <tr ng-click=\"goArticle(article.id)\" ng-repeat=\"article in filteredArticles | orderBy: orderProp\">\r" +
    "\n" +
    "            <td><strong>{{$index + 1}}</strong></td>\r" +
    "\n" +
    "            <td>[{{getKind(article.kind)}}]</td>\r" +
    "\n" +
    "            <td><strong>{{article.title}}</strong></td>\r" +
    "\n" +
    "            <td><span ng-repeat=\"tag in article.tags\">[{{tag}}] </span></td>\r" +
    "\n" +
    "            <td>{{article.date | date: 'HH:mm dd/MM/yyyy'}}</td>\r" +
    "\n" +
    "        </tr>\r" +
    "\n" +
    "    </table>\r" +
    "\n" +
    "</div>"
  );


  $templateCache.put('views/header.html',
    "<div>\r" +
    "\n" +
    "    <img src=\"../resources/gfx/logo320w.png\"\r" +
    "\n" +
    "        srcset=\"../resources/gfx/logo320w.png,\r" +
    "\n" +
    "                ../resources/gfx/logo500w.png\"\r" +
    "\n" +
    "        sizes=\"(min-width: 30em) 30em\"\r" +
    "\n" +
    "        alt=\"logo\"/>\r" +
    "\n" +
    "</div>"
  );


  $templateCache.put('views/single-storage.html',
    "<h2>{{language.stoTitle}}</h2>\r" +
    "\n" +
    "\r" +
    "\n" +
    "<jpadd storage=\"{{storage}}\"></jpadd>"
  );


  $templateCache.put('views/storage.html',
    "<div class=\"jplist\">\r" +
    "\n" +
    "    <h2>{{language.storage}}</h2>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <table>\r" +
    "\n" +
    "        <tr>\r" +
    "\n" +
    "            <th>{{language.no}}</th>\r" +
    "\n" +
    "            <th>{{language.kind}}</th>\r" +
    "\n" +
    "            <th>{{language.title}}</th>\r" +
    "\n" +
    "            <th>{{language.tags}}</th>\r" +
    "\n" +
    "            <th>{{language.date}}</th>\r" +
    "\n" +
    "        </tr>\r" +
    "\n" +
    "        <tr ng-click=\"goSingleStorage($index + 1)\" ng-repeat=\"article in storage | orderBy: date\">\r" +
    "\n" +
    "            <td><strong>{{$index + 1}}</strong></td>\r" +
    "\n" +
    "            <td>[{{getKind(article.kind)}}]</td>\r" +
    "\n" +
    "            <td><strong>{{article.title}}</strong></td>\r" +
    "\n" +
    "            <td><span ng-repeat=\"tag in article.tags\">[{{tag}}] </span></td>\r" +
    "\n" +
    "            <td>{{article.date | date: 'HH:mm dd/MM/yyyy'}}</td>\r" +
    "\n" +
    "        </tr>\r" +
    "\n" +
    "    </table>\r" +
    "\n" +
    "</div>"
  );


  $templateCache.put('views/subscriber.html',
    " <form ng-controller=\"subscribeCtrl\" name=\"sub\">\r" +
    "\n" +
    "    <div class=\"form-group\">\r" +
    "\n" +
    "        <label for=\"inputEmail3\" class=\"col-form-label\">{{language.subscribe}}</label>\r" +
    "\n" +
    "        <div>\r" +
    "\n" +
    "            <input type=\"email\" name=\"email\" ng-model=\"email\" class=\"form-control\" id=\"inputEmail3\" placeholder=\"Email\" required>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <small ng-show=\"sub.email.$error.email && sub.email.$dirty\" class=\"form-text jperror\">{{language.errEmail}}</small>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"form-group row\">\r" +
    "\n" +
    "        <div class=\"offset-sm-2 col-sm-10\">\r" +
    "\n" +
    "            <button ng-click=\"openModal()\" ng-disabled=\"sub.$invalid\" type=\"submit\" class=\"btn btn-primary\">{{language.submit}}</button>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    " </form>"
  );


  $templateCache.put('views/tagger.html',
    "<div ng-controller=\"tagCtrl\" class=\"tagparent\">\r" +
    "\n" +
    "    <div class=\"tagger\">\r" +
    "\n" +
    "        <span>\r" +
    "\n" +
    "            <i id=\"expander\" ng-if=\"!smallScreen\" class=\"fa fa-2x\" invoke=\"invoke\" main-id=\"#tags\" slide-toggle=\".slideable\" ng-model=\"animation.expanded\" ng-class=\"animation.expanded ? 'fa-hand-o-up' : 'fa-hand-o-down'\" aria-hidden=\"true\" uib-tooltip-html=\"animation.expanded ? animation.collapsedText : animation.expandedText\"></i>\r" +
    "\n" +
    "        </span>\r" +
    "\n" +
    "        <span id=\"login\" class=\"row-s\">\r" +
    "\n" +
    "            Zalogowany jako <strong>admin</strong>\r" +
    "\n" +
    "            <i class=\"fa fa-user fa-2x\" uib-tooltip=\"Logowanie\" aria-hidden=\"true\"></i>\r" +
    "\n" +
    "        </span>\r" +
    "\n" +
    "        <span class=\"row-s\">\r" +
    "\n" +
    "            <i class=\"fa fa-home fa-3x\" aria-hidden=\"true\" uib-tooltip=\"Home\" ng-click=\"goHome()\"></i>\r" +
    "\n" +
    "            <i class=\"fa fa-bars fa-3x\" aria-hidden=\"true\" uib-tooltip=\"Menu\" ng-click=\"openMenu()\"></i>\r" +
    "\n" +
    "            <i class=\"fa fa-tags fa-3x\" aria-hidden=\"true\" uib-tooltip=\"Tags\" ng-click=\"openTags()\"></i>\r" +
    "\n" +
    "            <i class=\"fa fa-eraser fa-3x\" aria-hidden=\"true\" uib-tooltip=\"Czyść\" ng-click=\"clear()\"></i>\r" +
    "\n" +
    "        </span>\r" +
    "\n" +
    "        <span class=\"row-s\">\r" +
    "\n" +
    "            <label class=\"row-s\" for=\"tag\">Wyszukiwanie po tagach</label>\r" +
    "\n" +
    "            <input class=\"form-control row-s\" typeahead-on-select=\"unfocus()\" ng-focus=\"focus()\" ng-blur=\"unfocus()\" ng-model=\"ngModelOptionsSelected\" ng-model-options=\"modelOptions\" uib-typeahead=\"tag for tag in tags | filter:$viewValue | limitTo: 5\" type=\"text\" id=\"tag\">\r" +
    "\n" +
    "        </span>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "    <jp-login login-id=\"#login\" is-admin=\"isAdmin\" add-redirect=\"goAdd\" storage-redirect=\"goStorage\"></jp-login>\r" +
    "\n" +
    "    <ul ng-class=\"isTagsOpened ? 'show-s' : 'hide-s'\" class=\"tagger slideable\" id=\"tags\">\r" +
    "\n" +
    "        <section style=\"display: table;\" class=\"row-s\">\r" +
    "\n" +
    "            <button ng-click=\"untickAll()\" class=\"btn btn-default col-s-6 show-s\">{{$root.language.clear}}</button>\r" +
    "\n" +
    "            <button ng-click=\"closeMenu()\" class=\"btn btn-default col-s-6 show-s\">{{$root.language.close}}</button>\r" +
    "\n" +
    "        </section>\r" +
    "\n" +
    "        <li class=\"x hide-s\" ng-click=\"untickAll()\" uib-tooltip=\"Odznacz tagi\" tooltip-placement=\"bottom\">X</li>\r" +
    "\n" +
    "        <li ng-class=\"selectedTags[$index] ? 'selected' : 'unselected'\" class=\"selected\" ng-click=\"tick($index)\" ng-init=\"render()\" ng-repeat=\"tag in tags\">{{tag}}</li>\r" +
    "\n" +
    "    </ul>\r" +
    "\n" +
    "</div>"
  );


  $templateCache.put('views/partials/partial.articles.html',
    "<div ui-view=\"articlesView\"></div>"
  );


  $templateCache.put('views/templates/jpadd.html',
    "<form name=\"addForm\">\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <jpcategory items=\"{{mockMenu}}\"></jpcategory>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"form-group\">\r" +
    "\n" +
    "        <label for=\"add-tags\">{{language.tags}}</label>\r" +
    "\n" +
    "        <textarea ng-pattern=\"pattern.tags\" ng-model=\"add.tags\" name=\"addTags\" class=\"form-control\" id=\"add-tags\" maxlength=\"1000\"\r" +
    "\n" +
    "                  aria-describedby=\"add tags\" rows=\"4\" jpcharacters></textarea>\r" +
    "\n" +
    "        <small class=\"form-text text-muted\">{{language.tagWarn}}</small>\r" +
    "\n" +
    "        <div ng-show=\"addForm.addTags.$error.jpcharacters\">\r" +
    "\n" +
    "            <span class=\"jperror\">{{language.errChars}}</span>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"form-group\">\r" +
    "\n" +
    "        <label for=\"add-title\">{{language.artTit}}</label>\r" +
    "\n" +
    "        <input maxlength=\"100\" type=\"text\" name=\"addTitle\" ng-model=\"add.title\" class=\"form-control\" id=\"add-title\" aria-describedby=\"title\" placeholder=\"{{language.insertTitle}}\" required>\r" +
    "\n" +
    "        <div ng-show=\"addForm.addTitle.$error.required\">\r" +
    "\n" +
    "            <span class=\"jperror\">{{language.errReq}}</span>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <small class=\"form-text text-muted\">{{language.errMax}} 100</small>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"form-group\">\r" +
    "\n" +
    "        <label for=\"add-intro\">{{language.artIntro}}</label>\r" +
    "\n" +
    "        <textarea class=\"form-control\" name=\"addIntro\" ng-model=\"add.intro\" id=\"add-intro\" aria-describedby=\"intro\" rows=\"4\" required></textarea>\r" +
    "\n" +
    "        <div ng-show=\"addForm.addIntro.$error.required\">\r" +
    "\n" +
    "            <span class=\"jperror\">{{language.errReq}}</span>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <small class=\"form-text text-muted\">{{language.artIntroInf}}</small>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <jppostarea form=\"addForm\" rows=\"20\"></jppostarea>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"input-group add-youtube\">\r" +
    "\n" +
    "        <span class=\"input-group-addon\">\r" +
    "\n" +
    "            <input ng-model=\"youtubeEnabled\" ng-true-value=\"true\" ng-false-value=\"false\" type=\"checkbox\" aria-label=\"youtube checkbox\">\r" +
    "\n" +
    "        </span>\r" +
    "\n" +
    "        <span class=\"input-group-addon\" id=\"sizing-addon1\">www</span>\r" +
    "\n" +
    "        <input ng-disabled=\"!youtubeEnabled\" type=\"url\" placeholder=\"youtube link\" class=\"form-control\" aria-label=\"youtube input.\">\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"add-submit\">\r" +
    "\n" +
    "        <button ng-click=\"openModalSaveToStorage()\" class=\"btn btn-primary\">{{language.stoSave}}</button>\r" +
    "\n" +
    "        <button ng-click=\"openModalSubmit()\" ng-disabled=\"addForm.$invalid\" class=\"btn btn-primary\">{{language.publish}}</button>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "</form>"
  );


  $templateCache.put('views/templates/jparticle.html',
    "<article class=\"jparticle\">\r" +
    "\n" +
    "    <header>\r" +
    "\n" +
    "        <div>\r" +
    "\n" +
    "            <span class=\"author\">{{nauthor}}</span>\r" +
    "\n" +
    "            <span class=\"date\">{{ndate | date: 'HH:mm dd/MM/yyyy'}}</span>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <h2 contenteditable=\"false\">{{ntitle}}</h2>\r" +
    "\n" +
    "        <div>\r" +
    "\n" +
    "            <button ng-show=\"isEditable\" class=\"btn btn-default\" ng-click=\"submit()\">{{$root.language.submit}}</button>\r" +
    "\n" +
    "            <button ng-hide=\"isEditable\" class=\"btn btn-default\" ng-click=\"edit()\">{{$root.language.edit}}</button>\r" +
    "\n" +
    "            <button ng-show=\"isEditable\" class=\"btn btn-default\" ng-click=\"cancel()\">{{$root.language.cancel}}</button>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "    </header>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"tags\">\r" +
    "\n" +
    "        <span ng-hide=\"angular.isUndefined(ntags) || ntags.length === 0\">{{$root.language.tagsS}}</span><span ng-repeat=\"tag in ntags\">{{tag}}</span>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "    <p contenteditable=\"false\" class=\"intro\">{{nintro}}</p>\r" +
    "\n" +
    "    <p contenteditable=\"false\" class=\"content\" ng-bind-html=\"ncontent\"></p>\r" +
    "\n" +
    "    <div class=\"footer\">\r" +
    "\n" +
    "        <span>{{$root.language.comments}} [{{ncomments.length}}]</span>\r" +
    "\n" +
    "        <i class=\"fa fa-youtube fa-2x\" aria-hidden=\"true\" uib-tooltip=\"{{$root.language.ybWatch}}\"></i>\r" +
    "\n" +
    "        <i class=\"fa fa-file-pdf-o fa-2x\" aria-hidden=\"true\" uib-tooltip=\"{{$root.language.pdfDownload}}\"></i>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "    <form novalidate ng-submit=\"nadd(usercom.author, usercom.content)\" name=\"form\" class=\"add\">\r" +
    "\n" +
    "        <div class=\"form-group\">\r" +
    "\n" +
    "            <label for=\"commentcontent\">{{$root.language.addComment}}</label>\r" +
    "\n" +
    "            <textarea name=\"content\" ng-class=\"notUrls(usercom.content) || form.content.$error.maxlength ? 'jperror' : ''\" ng-model=\"usercom.content\"\r" +
    "\n" +
    "                      class=\"form-control\" id=\"commentcontent\" placeholder=\"{{$root.language.commentContent}}\" rows=\"3\"\r" +
    "\n" +
    "                      ng-minlength=\"3\" ng-maxlength=\"500\" ng-trim=\"true\" required></textarea>\r" +
    "\n" +
    "            <div ng-show=\"notUrls(usercom.content)\" class=\"jperror\">{{$root.language.urlPrevent}}</div>\r" +
    "\n" +
    "            <div ng-show=\"form.content.$error.maxlength\" class=\"jperror\">{{$root.language.commentLimit}}</div>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <div class=\"input-group\">\r" +
    "\n" +
    "            <span class=\"input-group-addon\" id=\"basic-addon1\">{{$root.language.username}}</span>\r" +
    "\n" +
    "            <input name=\"username\" ng-class=\"form.content.$error.pattern || form.content.$error.maxlength ? 'jperror' : ''\" ng-model=\"usercom.author\"\r" +
    "\n" +
    "                   class=\"form-control\" placeholder=\"{{$root.language.username}}\"\r" +
    "\n" +
    "                   aria-describedby=\"basic-addon1\" ng-minlength=\"3\" ng-maxlength=\"15\" ng-pattern=\"pattern.user\" ng-trim=\"true\" required>\r" +
    "\n" +
    "            <span class=\"input-group-btn\">\r" +
    "\n" +
    "                <input ng-disabled=\"form.username.$invalid || form.username.$invalid || notUrls(usercom.content) || form.content.$invalid\" id=\"submit\" class=\"btn btn-secondary\" type=\"submit\">{{$root.language.add}}</input>\r" +
    "\n" +
    "            </span>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <div ng-show=\"form.username.$error.pattern\" class=\"jperror\">{{$root.language.errSings}}</div>\r" +
    "\n" +
    "        <div ng-show=\"form.username.$error.maxlength\" class=\"jperror\">{{$root.language.errExceed}}</div>\r" +
    "\n" +
    "    </form>\r" +
    "\n" +
    "    <div class=\"comment\" ng-repeat=\"ncomment in ncomments\"><div><span>#{{$index + 1}}</span><strong>{{ncomment.author}}</strong> {{$root.language.wrote}} {{ncomment.date | date: 'HH:mm dd/MM/yyyy'}}:\r" +
    "\n" +
    "    </div><button ng-click=\"remove($index)\" class=\"btn btn-default\">{{$root.language.remove}}</button>{{ncomment.content}}</div>\r" +
    "\n" +
    "</article>"
  );


  $templateCache.put('views/templates/jpcontact.html',
    "<section class=\"contact\">\r" +
    "\n" +
    "    <form name=\"contact\">\r" +
    "\n" +
    "        <h2>{{$root.language.contact}}</h2>\r" +
    "\n" +
    "\r" +
    "\n" +
    "        <div class=\"col-md-6\">\r" +
    "\n" +
    "            <div class=\"form-group\">\r" +
    "\n" +
    "                <label for=\"foot-email\">{{$root.language.emailAddress}}</label>\r" +
    "\n" +
    "                <input type=\"email\" ng-model=\"email\" name=\"email\" class=\"form-control\" id=\"foot-email\" aria-describedby=\"email\" placeholder=\"{{$root.language.insertEmail}}\" required>\r" +
    "\n" +
    "                <small id=\"emailHelp\" class=\"form-text text-muted\">{{$root.language.emailOrg}}</small>\r" +
    "\n" +
    "                <small ng-show=\"contact.email.$error.email && contact.email.$dirty\" class=\"form-text jperror smerr\">{{$root.language.errEmail}}</small>\r" +
    "\n" +
    "                <small ng-show=\"contact.email.$error.required && contact.email.$dirty\" class=\"form-text jperror smerr\">{{$root.language.errReq}}</small>\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "            <div class=\"form-group\">\r" +
    "\n" +
    "                <label for=\"foot-title\">{{$root.language.emailTitle}}</label>\r" +
    "\n" +
    "                <input maxlength=\"100\" type=\"text\" name=\"title\" ng-model=\"title\" class=\"form-control\" id=\"foot-title\" aria-describedby=\"title\" placeholder=\"{{$root.language.emailTitle}}\" required jpalphanumeric>\r" +
    "\n" +
    "                <small id=\"title\" class=\"form-text text-muted\">{{$root.language.errMax}} 100</small>\r" +
    "\n" +
    "                <small ng-show=\"contact.title.$error.required && contact.title.$dirty\" class=\"form-text jperror smerr\">{{$root.language.errReq}}</small>\r" +
    "\n" +
    "                <small ng-show=\"contact.title.$error.jpalphanumeric && contact.title.$dirty\" class=\"form-text jperror smerr\">{{$root.language.errAlphanum}}</small>\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "        <div class=\"col-md-6\">\r" +
    "\n" +
    "            <div class=\"form-group\">\r" +
    "\n" +
    "                <label for=\"foot-content\">{{$root.language.emailContent}}</label>\r" +
    "\n" +
    "                <textarea maxlength=\"1000\" class=\"form-control\" id=\"foot-content\" aria-describedby=\"content\" rows=\"6\"></textarea>\r" +
    "\n" +
    "                <small id=\"content\" class=\"form-text text-muted\">{{$root.language.errMax}} 1000</small>\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "            <div class=\"form-group send\">\r" +
    "\n" +
    "                <button ng-click=\"vm.send()\" ng-disabled=\"contact.$invalid\" type=\"submit\" class=\"btn btn-primary\">{{$root.language.send}}</button>\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "    </form>\r" +
    "\n" +
    "</section>"
  );


  $templateCache.put('views/templates/jplogin.html',
    "<form name=\"loginForm\" ng-hide=\"isAdmin\" class=\"ng-pristine ng-valid\">\r" +
    "\n" +
    "    <div class=\"form-group row\">\r" +
    "\n" +
    "        <div class=\"col-sm-12\">\r" +
    "\n" +
    "            <input type=\"text\" name=\"user\" ng-model=\"user\" class=\"form-control\" placeholder=\"{{$root.language.username}}\" required>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <small ng-show=\"loginForm.user.$error.required && loginForm.user.$dirty\" class=\"form-text jperror\">{{$root.language.errReq}}</small>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"form-group row\">\r" +
    "\n" +
    "        <div class=\"col-sm-12\">\r" +
    "\n" +
    "            <input type=\"password\" name=\"pass\" ng-model=\"pass\" class=\"form-control\" placeholder=\"{{$root.language.password}}\" required>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <small ng-show=\"loginForm.pass.$error.required && loginForm.pass.$dirty\" class=\"form-text jperror\">{{$root.language.errReq}}</small>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"row\">\r" +
    "\n" +
    "        <button type=\"submit\" ng-disabled=\"loginForm.$invalid\" ng-click=\"login(user, pass)\" class=\"btn btn-primary\">{{$root.language.login}}</button>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "</form>\r" +
    "\n" +
    "\r" +
    "\n" +
    "<form ng-show=\"isAdmin\" class=\"ng-pristine ng-valid\">\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"form-group\">\r" +
    "\n" +
    "        <button ng-click=\"addRedirect()\" class=\"btn btn-default jpfullwidth\">{{$root.language.artAdd}}</button>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"form-group\">\r" +
    "\n" +
    "        <button ng-click=\"storageRedirect()\" class=\"btn btn-default jpfullwidth\">{{$root.language.stoReview}}</button>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "    <div class=\"row\">\r" +
    "\n" +
    "        <button type=\"submit\" ng-disabled=\"loginForm.$invalid\" ng-click=\"logout()\" class=\"btn btn-primary\">{{$root.language.logout}}</button>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "\r" +
    "\n" +
    "</form>"
  );


  $templateCache.put('views/templates/jpnode.html',
    "<div jpnode class='node'\r" +
    "\n" +
    "     ng-click=\"item.submenus && item.submenus.length > 0 ? toggle(this) : select(this, item)\"\r" +
    "\n" +
    "     ng-class=\"{click: !(item.submenus && item.submenus.length > 0), selected: selected}\"\r" +
    "\n" +
    "     ui-tree-handle>\r" +
    "\n" +
    "    <i ng-show=\"item.submenus && item.submenus.length > 0\" class=\"fa\"\r" +
    "\n" +
    "       ng-class=\"collapsed ? 'fa-caret-square-o-down' : 'fa-caret-square-o-up'\"\r" +
    "\n" +
    "       aria-hidden=\"true\" data-nodrag>\r" +
    "\n" +
    "    </i>\r" +
    "\n" +
    "    {{item.name}}\r" +
    "\n" +
    "</div>\r" +
    "\n" +
    "<ol ui-tree-nodes='' ng-model='item.submenus' ng-class='{hidden: collapsed}'>\r" +
    "\n" +
    "    <li ng-repeat='item in item.submenus track by $index' ui-tree-node ng-include=\"'views/templates/jpnode.html'\"></li>\r" +
    "\n" +
    "</ol>"
  );


  $templateCache.put('views/templates/jppostarea.html',
    "<ng-form name=\"postarea\">\r" +
    "\n" +
    "    <div class=\"postarea form-group\">\r" +
    "\n" +
    "        <div class=\"display\" ng-show=\"show\"><button ng-click=\"show = false\">x</button><p mathjax-bind></p></div>\r" +
    "\n" +
    "        <label for=\"add-content\">{{$root.language.artContent}}</label>\r" +
    "\n" +
    "        <textarea id=\"add-content\" ng-model=\"expression\" name=\"post\" class=\"form-control\" rows=\"{{rows}}\"\r" +
    "\n" +
    "                  ng-trim=\"false\" required></textarea>\r" +
    "\n" +
    "        <div ng-show=\"postarea.post.$error.required\">\r" +
    "\n" +
    "            <span class=\"jperror\">{{$root.language.errReq}}</span>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <div class=\"controls form-group\">\r" +
    "\n" +
    "            <div>\r" +
    "\n" +
    "                <button ng-click=\"appendLatex()\" class=\"btn btn-default\">LaTeX</button>\r" +
    "\n" +
    "                <button ng-click=\"appendPhoto()\" class=\"btn btn-default\">{{$root.language.graphic}}</button>\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "            <div>\r" +
    "\n" +
    "                <button ng-click=\"preview()\" class=\"btn btn-default\">{{$root.language.preview}}</button>\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "</ng-form>"
  );


  $templateCache.put('views/templates/modal.html',
    "<div class=\"modal-body\">{{msg}}</div>\r" +
    "\n" +
    "<div class=\"modal-footer\">\r" +
    "\n" +
    "    <button class=\"btn btn-primary\" ng-click=\"ok()\">OK</button>\r" +
    "\n" +
    "    <button class=\"btn btn-warning\" ng-click=\"cancel()\">{{language.cancel}}</button>\r" +
    "\n" +
    "</div>"
  );

}]);
