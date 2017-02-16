angular.module('jakPoliczycApp')
    .constant('mockTags',
        [ 'Funkcje', 'Algebra', 'Bryły', 'Analiza', 'Geometria', 'Analityka', 'Trygonometria',
            'Prawdopodobieństwo', 'Kombinatoryka', 'Topologia', 'Stereometria', 'Wielomiany', 'Logarytmy',
            'Całki', 'Pochodne', 'Tales', 'Pitagoras', 'Równania' ]
    )

    .constant('mockMenu',
        [
            {
                "id": 1,
                "name": "Podstawowe własności figur geometrycznych na płaszczyźnie",
                "submenus": [
                    {
                        "id": 2,
                        "name": "Okrąg i koło",
                        "submenus": [
                            {"id": 11, "name": "Wzajemne położenie prostej i okręgu"},
                            {"id": 12, "name": "Wzajemne położenie dwóch okręgów"},
                            {"id": 13, "name": "Kąty i koła"}
                        ]
                    },
                    {
                        "id": 3,
                        "name": "Czworokąty",
                        "submenus": [
                            {"id": 14, "name": "Trapezy"},
                            {"id": 15, "name": "Równoległoboki"},
                            {"id": 16, "name": "Trapezoidy"}
                        ]
                    },
                    {
                        "id": 4,
                        "name": "Wielokąty wpisane w okrąg i opisane na okręgu",
                        "submenus": [
                            {"id": 17, "name": "Trójkąt wpisany w okrąg i opisany na okręgu"},
                            {"id": 18, "name": "Okrąg wpisany w czworokąt"},
                            {"id": 19, "name": "Okrąg opisany na czworokącie"}
                        ]
                    }
                ]
            },
            {
                "id": 5,
                "name": "Funkcja kwadratowa",
                "submenus": [
                    {
                        "id": 6,
                        "name": "Jednomian stopnia drugiego"
                    },
                    {
                        "id": 7,
                        "name": "Postać ogólna i kanoniczna funkcji kwadratowej"
                    },
                    {
                        "id": 8,
                        "name": "Miejsce zerowe i postać iloczynowa trójmianu kwadratowego"
                    },
                    {
                        "id": 9,
                        "name": "Równania kwadratowe",
                        "submenus": [
                            {"id": 20, "name": "Rownania prowadzące do równań kwadratowych"}
                        ]
                    }
                ]
            },
            {
                "id": 10,
                "name": "Funkcja liniowa",
                "submenus": []
            }
        ]
    )

    .constant('mockArticles',
        [
            {
                "id": 1,
                "kind": "Z",
                "title": "Carlson Dalton",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Bryły",
                    "Analiza",
                    "Równania"
                ]
            },
            {
                "id": 2,
                "kind": "Z",
                "title": "Wise Watson",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Stereometria",
                    "Logarytmy",
                    "Wielomiany"
                ]
            },
            {
                "id": 3,
                "kind": "T",
                "title": "Edwina Rosa",
                "date": 1480167949,
                "menuId": 13,
                "tags": [
                    "Algebra",
                    "Analiza",
                    "Całki"
                ]
            },
            {
                "id": 4,
                "kind": "T",
                "title": "Gwendolyn Owen",
                "date": 1480167949,
                "menuId": 14,
                "tags": [
                    "Funkcje",
                    "Kombinatoryka"
                ]
            },
            {
                "id": 5,
                "kind": "T",
                "title": "Puckett Byrd",
                "date": 1480167949,
                "menuId": 15,
                "tags": []
            },
            {
                "id": 6,
                "kind": "Z",
                "title": "Stanley Herman",
                "date": 1480167949,
                "menuId": 15,
                "tags": [
                    "Pitagoras"
                ]
            },
            {
                "id": 7,
                "kind": "Z",
                "title": "Jakieś tam trudne funkcje kwadratowe",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Bryły",
                    "Analiza",
                    "Równania"
                ]
            },
            {
                "id": 8,
                "kind": "Z",
                "title": "Trygonometria to złooooo",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Stereometria",
                    "Logarytmy",
                    "Wielomiany"
                ]
            },
            {
                "id": 9,
                "kind": "T",
                "title": "Całki są spoko, wszystko nimi policzysz",
                "date": 1480167949,
                "menuId": 13,
                "tags": [
                    "Algebra",
                    "Analiza",
                    "Całki"
                ]
            },
            {
                "id": 10,
                "kind": "T",
                "title": "Gwendolyn Owen",
                "date": 1480167949,
                "menuId": 14,
                "tags": [
                    "Funkcje",
                    "Kombinatoryka"
                ]
            },
            {
                "id": 11,
                "kind": "T",
                "title": "Już mi się nie chce wyświetlać tych tutaj rzeczy",
                "date": 1480167949,
                "menuId": 15,
                "tags": []
            },
            {
                "id": 12,
                "kind": "Z",
                "title": "Stanley Herman",
                "date": 1480167949,
                "menuId": 15,
                "tags": [
                    "Pitagoras"
                ]
            },
            {
                "id": 13,
                "kind": "Z",
                "title": "Carlson Dalton",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Bryły",
                    "Analiza",
                    "Równania"
                ]
            },
            {
                "id": 14,
                "kind": "Z",
                "title": "Paliwo kosztuje za dużo",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Stereometria",
                    "Logarytmy",
                    "Wielomiany"
                ]
            },
            {
                "id": 15,
                "kind": "T",
                "title": "Edwina Rosa",
                "date": 1480167949,
                "menuId": 13,
                "tags": [
                    "Algebra",
                    "Analiza",
                    "Całki"
                ]
            },
            {
                "id": 16,
                "kind": "T",
                "title": "Funkcja liniowa pozwala na wszystko",
                "date": 1480167949,
                "menuId": 14,
                "tags": [
                    "Funkcje",
                    "Kombinatoryka"
                ]
            },
            {
                "id": 17,
                "kind": "T",
                "title": "Gang Kombinatoryków znowu uderzył",
                "date": 1480167949,
                "menuId": 15,
                "tags": []
            },
            {
                "id": 18,
                "kind": "Z",
                "title": "Stanley Herman",
                "date": 1480167949,
                "menuId": 15,
                "tags": [
                    "Pitagoras"
                ]
            },
            {
                "id": 19,
                "kind": "Z",
                "title": "Carlson Dalton",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Bryły",
                    "Analiza",
                    "Równania"
                ]
            },
            {
                "id": 20,
                "kind": "Z",
                "title": "Wise Watson",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Stereometria",
                    "Logarytmy",
                    "Wielomiany"
                ]
            },
            {
                "id": 21,
                "kind": "T",
                "title": "Edwina Rosa",
                "date": 1480167949,
                "menuId": 13,
                "tags": [
                    "Algebra",
                    "Analiza",
                    "Całki"
                ]
            },
            {
                "id": 22,
                "kind": "T",
                "title": "Gwendolyn Owen",
                "date": 1480167949,
                "menuId": 14,
                "tags": [
                    "Funkcje",
                    "Kombinatoryka"
                ]
            },
            {
                "id": 23,
                "kind": "T",
                "title": "Puckett Byrd",
                "date": 1480167949,
                "menuId": 15,
                "tags": []
            },
            {
                "id": 24,
                "kind": "Z",
                "title": "Stanley Herman",
                "date": 1480167949,
                "menuId": 15,
                "tags": [
                    "Pitagoras"
                ]
            }
        ]
    )

    .constant('mockStorage',
        [
            {
                "id": 1,
                "kind": "Z",
                "title": "Carlson Dalton",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Bryły",
                    "Analiza",
                    "Równania"
                ]
            },
            {
                "id": 2,
                "kind": "Z",
                "title": "Wise Watson",
                "date": 1480167949,
                "menuId": 11,
                "tags": [
                    "Stereometria",
                    "Logarytmy",
                    "Wielomiany"
                ]
            },
            {
                "id": 3,
                "kind": "T",
                "title": "Edwina Rosa",
                "date": 1480167949,
                "menuId": 13,
                "tags": [
                    "Algebra",
                    "Analiza",
                    "Całki"
                ]
            },
            {
                "id": 4,
                "kind": "T",
                "title": "Gwendolyn Owen",
                "date": 1480167949,
                "menuId": 14,
                "tags": [
                    "Funkcje",
                    "Kombinatoryka"
                ]
            }
        ]
    )

    .constant('mockArticle',

        [
            {
                "id": 1,
                "kind": "Z",
                "title": "Carlson Dalton",
                "intro": "Non fugiat in elit amet nulla fugiat aliquip enim. Culpa aliquip amet deserunt mollit fugiat in irure esse velit occaecat. Consectetur minim aute enim deserunt in minim ad culpa aliquip duis aliqua.\r\n",
                "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum libero nibh, et tincidunt lacus malesuada eget. Duis lacus lacus, luctus quis tellus at, viverra condimentum sem. Sed non tellus dolor. Maecenas volutpat scelerisque augue, ac scelerisque eros convallis vitae. Donec volutpat lorem quis commodo fringilla. Cras pretium eu nisl non pellentesque. Cras vehicula nibh diam, vel pharetra lorem eleifend sed.<br/><br/> Suspendisse egestas placerat urna, eu tristique arcu consequat sit amet. Sed rutrum sed leo sed condimentum. Proin ultricies nisl arcu, quis blandit nunc gravida a. Phasellus posuere vestibulum aliquet. Donec nec elit id velit tincidunt pharetra nec in dolor.<br/><br/> In pretium non risus at lacinia. Morbi ornare hendrerit quam, at ultrices libero aliquet quis. Nunc bibendum facilisis metus, sit amet scelerisque ipsum blandit a. In interdum lorem sed dui pharetra, sit amet finibus nulla pulvinar. Duis eget diam lorem. Fusce blandit ipsum ac mollis gravida. Suspendisse ante ante, ornare in rutrum a, hendrerit ut nunc. In sollicitudin et elit ut ultrices. Proin blandit enim suscipit pretium eleifend. Sed scelerisque tellus feugiat mi elementum consectetur. Phasellus quis nunc pharetra, tempus dolor in, vehicula odio. Pellentesque justo elit, varius vel turpis sit amet, laoreet elementum lectus. Nunc convallis, lacus non suscipit congue, dolor nunc iaculis nulla, a molestie nisi libero in urna. Donec arcu ligula, aliquam vitae accumsan ut, cursus ac erat. Maecenas at erat sodales nunc finibus suscipit ut sit amet nisl. Praesent nec ultricies felis. Pellentesque tempor auctor urna sit amet varius. Vestibulum molestie luctus risus non tempor. Quisque nec eros leo. Curabitur pharetra ligula in consectetur pretium. Curabitur pellentesque gravida magna, a vulputate dolor. Fusce quis aliquet mi. Donec hendrerit pellentesque ipsum nec lacinia. Morbi at ultricies augue. Donec rutrum leo sit amet purus blandit ullamcorper. Nam augue lectus, vestibulum sollicitudin suscipit quis, maximus non enim. Curabitur molestie interdum enim in semper. In malesuada lorem ut neque cursus, sit amet consectetur ante vehicula. Nunc hendrerit lorem eget scelerisque suscipit. Etiam tempus nisi sed aliquet venenatis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi nibh libero, ullamcorper at sodales ac, posuere et ipsum. Nunc dictum egestas mattis. Nunc in sollicitudin libero. In hac habitasse platea dictumst. Sed nec tristique augue. Ut nec blandit risus. Vestibulum vitae magna condimentum nunc ultrices molestie non vel velit. Duis bibendum facilisis nisl bibendum lacinia. Quisque maximus nulla quis massa feugiat, vitae auctor mi posuere. In eu justo eget odio fringilla sagittis. Duis luctus eget lorem nec lacinia. Proin eu dapibus orci, id faucibus ante. Aliquam bibendum magna ut libero laoreet interdum. Sed leo est, hendrerit vel nibh nec, pharetra convallis elit. In nibh erat, tempor vel augue id, aliquet lacinia massa. Proin mattis luctus ipsum at interdum. Integer hendrerit lectus sit amet tempus commodo. Cras vel quam lectus. Aenean molestie sagittis nulla, dapibus varius ligula varius at. Donec nisl erat, lobortis quis mi in, faucibus lobortis nisi. Duis sollicitudin felis vitae elit venenatis pharetra. Nulla sed bibendum ante. Nunc eget magna at tortor blandit fringilla sed id leo. Maecenas tristique ut arcu lobortis iaculis. Suspendisse tincidunt nulla nec feugiat viverra. Etiam vulputate nulla eget mollis ornare. Nullam blandit, nisi id rhoncus laoreet, urna libero cursus nisl, et aliquam dolor est sit amet neque. Nam mattis ex accumsan augue molestie, sit amet malesuada ante ornare. Praesent id sem tristique libero tincidunt dictum. Vivamus nunc orci, consectetur vel ipsum in, posuere commodo purus. Aenean fringilla sodales magna. Aliquam tempus est non purus faucibus elementum. Ut dui arcu, pulvinar sit amet orci ac, ultrices dictum ligula. Donec nunc nibh, venenatis quis euismod eu, malesuada in nisi. Aliquam in dolor feugiat, sagittis lorem eu, tincidunt ex. In augue libero, sodales a purus quis, sodales ornare quam. Sed at accumsan augue. In tincidunt convallis porta. Suspendisse lobortis gravida tellus non tristique. Duis molestie a velit a tincidunt. Fusce feugiat imperdiet mollis. Vivamus rutrum congue est, nec consequat lorem congue eu.",
                "date": 1480167949,
                "menuId": 11,
                "comments": [
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    },
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    },
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    }
                ],
                "tags": [
                    "Bryły",
                    "Analiza",
                    "Równania"
                ]
            },
            {
                "id": 2,
                "kind": "Z",
                "title": "Wise Watson",
                "intro": "Nulla Lorem magna deserunt nisi nulla aliquip voluptate tempor sit. Pariatur laboris enim eu pariatur aute veniam officia amet velit ut magna sit. Ea in voluptate do in sunt duis sit exercitation labore.\r\n",
                "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum libero nibh, et tincidunt lacus malesuada eget. Duis lacus lacus, luctus quis tellus at, viverra condimentum sem. Sed non tellus dolor. Maecenas volutpat scelerisque augue, ac scelerisque eros convallis vitae. Donec volutpat lorem quis commodo fringilla. Cras pretium eu nisl non pellentesque. Cras vehicula nibh diam, vel pharetra lorem eleifend sed. Suspendisse egestas placerat urna, eu tristique arcu consequat sit amet. Sed rutrum sed leo sed condimentum. Proin ultricies nisl arcu, quis blandit nunc gravida a. Phasellus posuere vestibulum aliquet. Donec nec elit id velit tincidunt pharetra nec in dolor. In pretium non risus at lacinia. Morbi ornare hendrerit quam, at ultrices libero aliquet quis. Nunc bibendum facilisis metus, sit amet scelerisque ipsum blandit a. In interdum lorem sed dui pharetra, sit amet finibus nulla pulvinar. Duis eget diam lorem. Fusce blandit ipsum ac mollis gravida. Suspendisse ante ante, ornare in rutrum a, hendrerit ut nunc. In sollicitudin et elit ut ultrices. Proin blandit enim suscipit pretium eleifend. Sed scelerisque tellus feugiat mi elementum consectetur. Phasellus quis nunc pharetra, tempus dolor in, vehicula odio. Pellentesque justo elit, varius vel turpis sit amet, laoreet elementum lectus. Nunc convallis, lacus non suscipit congue, dolor nunc iaculis nulla, a molestie nisi libero in urna. Donec arcu ligula, aliquam vitae accumsan ut, cursus ac erat. Maecenas at erat sodales nunc finibus suscipit ut sit amet nisl. Praesent nec ultricies felis. Pellentesque tempor auctor urna sit amet varius. Vestibulum molestie luctus risus non tempor. Quisque nec eros leo. Curabitur pharetra ligula in consectetur pretium. Curabitur pellentesque gravida magna, a vulputate dolor. Fusce quis aliquet mi. Donec hendrerit pellentesque ipsum nec lacinia. Morbi at ultricies augue. Donec rutrum leo sit amet purus blandit ullamcorper. Nam augue lectus, vestibulum sollicitudin suscipit quis, maximus non enim. Curabitur molestie interdum enim in semper. In malesuada lorem ut neque cursus, sit amet consectetur ante vehicula. Nunc hendrerit lorem eget scelerisque suscipit. Etiam tempus nisi sed aliquet venenatis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi nibh libero, ullamcorper at sodales ac, posuere et ipsum. Nunc dictum egestas mattis. Nunc in sollicitudin libero. In hac habitasse platea dictumst. Sed nec tristique augue. Ut nec blandit risus. Vestibulum vitae magna condimentum nunc ultrices molestie non vel velit. Duis bibendum facilisis nisl bibendum lacinia. Quisque maximus nulla quis massa feugiat, vitae auctor mi posuere. In eu justo eget odio fringilla sagittis. Duis luctus eget lorem nec lacinia. Proin eu dapibus orci, id faucibus ante. Aliquam bibendum magna ut libero laoreet interdum. Sed leo est, hendrerit vel nibh nec, pharetra convallis elit. In nibh erat, tempor vel augue id, aliquet lacinia massa. Proin mattis luctus ipsum at interdum. Integer hendrerit lectus sit amet tempus commodo. Cras vel quam lectus. Aenean molestie sagittis nulla, dapibus varius ligula varius at. Donec nisl erat, lobortis quis mi in, faucibus lobortis nisi. Duis sollicitudin felis vitae elit venenatis pharetra. Nulla sed bibendum ante. Nunc eget magna at tortor blandit fringilla sed id leo. Maecenas tristique ut arcu lobortis iaculis. Suspendisse tincidunt nulla nec feugiat viverra. Etiam vulputate nulla eget mollis ornare. Nullam blandit, nisi id rhoncus laoreet, urna libero cursus nisl, et aliquam dolor est sit amet neque. Nam mattis ex accumsan augue molestie, sit amet malesuada ante ornare. Praesent id sem tristique libero tincidunt dictum. Vivamus nunc orci, consectetur vel ipsum in, posuere commodo purus. Aenean fringilla sodales magna. Aliquam tempus est non purus faucibus elementum. Ut dui arcu, pulvinar sit amet orci ac, ultrices dictum ligula. Donec nunc nibh, venenatis quis euismod eu, malesuada in nisi. Aliquam in dolor feugiat, sagittis lorem eu, tincidunt ex. In augue libero, sodales a purus quis, sodales ornare quam. Sed at accumsan augue. In tincidunt convallis porta. Suspendisse lobortis gravida tellus non tristique. Duis molestie a velit a tincidunt. Fusce feugiat imperdiet mollis. Vivamus rutrum congue est, nec consequat lorem congue eu.",
                "date": 1480167949,
                "menuId": 11,
                "comments": [],
                "tags": [
                    "Stereometria",
                    "Logarytmy",
                    "Wielomiany"
                ]
            },
            {
                "id": 3,
                "kind": "T",
                "title": "Edwina Rosa",
                "intro": "Consectetur fugiat cupidatat minim mollit anim pariatur qui labore ea ea sit commodo. Sint labore veniam mollit amet dolor officia cupidatat consequat esse sint pariatur culpa sit. Nulla adipisicing non non esse elit reprehenderit consectetur laborum. Sunt veniam occaecat ad tempor officia quis.\r\n",
                "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum libero nibh, et tincidunt lacus malesuada eget. Duis lacus lacus, luctus quis tellus at, viverra condimentum sem. Sed non tellus dolor. Maecenas volutpat scelerisque augue, ac scelerisque eros convallis vitae. Donec volutpat lorem quis commodo fringilla. Cras pretium eu nisl non pellentesque. Cras vehicula nibh diam, vel pharetra lorem eleifend sed. Suspendisse egestas placerat urna, eu tristique arcu consequat sit amet. Sed rutrum sed leo sed condimentum. Proin ultricies nisl arcu, quis blandit nunc gravida a. Phasellus posuere vestibulum aliquet. Donec nec elit id velit tincidunt pharetra nec in dolor. In pretium non risus at lacinia. Morbi ornare hendrerit quam, at ultrices libero aliquet quis. Nunc bibendum facilisis metus, sit amet scelerisque ipsum blandit a. In interdum lorem sed dui pharetra, sit amet finibus nulla pulvinar. Duis eget diam lorem. Fusce blandit ipsum ac mollis gravida. Suspendisse ante ante, ornare in rutrum a, hendrerit ut nunc. In sollicitudin et elit ut ultrices. Proin blandit enim suscipit pretium eleifend. Sed scelerisque tellus feugiat mi elementum consectetur. Phasellus quis nunc pharetra, tempus dolor in, vehicula odio. Pellentesque justo elit, varius vel turpis sit amet, laoreet elementum lectus. Nunc convallis, lacus non suscipit congue, dolor nunc iaculis nulla, a molestie nisi libero in urna. Donec arcu ligula, aliquam vitae accumsan ut, cursus ac erat. Maecenas at erat sodales nunc finibus suscipit ut sit amet nisl. Praesent nec ultricies felis. Pellentesque tempor auctor urna sit amet varius. Vestibulum molestie luctus risus non tempor. Quisque nec eros leo. Curabitur pharetra ligula in consectetur pretium. Curabitur pellentesque gravida magna, a vulputate dolor. Fusce quis aliquet mi. Donec hendrerit pellentesque ipsum nec lacinia. Morbi at ultricies augue. Donec rutrum leo sit amet purus blandit ullamcorper. Nam augue lectus, vestibulum sollicitudin suscipit quis, maximus non enim. Curabitur molestie interdum enim in semper. In malesuada lorem ut neque cursus, sit amet consectetur ante vehicula. Nunc hendrerit lorem eget scelerisque suscipit. Etiam tempus nisi sed aliquet venenatis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi nibh libero, ullamcorper at sodales ac, posuere et ipsum. Nunc dictum egestas mattis. Nunc in sollicitudin libero. In hac habitasse platea dictumst. Sed nec tristique augue. Ut nec blandit risus. Vestibulum vitae magna condimentum nunc ultrices molestie non vel velit. Duis bibendum facilisis nisl bibendum lacinia. Quisque maximus nulla quis massa feugiat, vitae auctor mi posuere. In eu justo eget odio fringilla sagittis. Duis luctus eget lorem nec lacinia. Proin eu dapibus orci, id faucibus ante. Aliquam bibendum magna ut libero laoreet interdum. Sed leo est, hendrerit vel nibh nec, pharetra convallis elit. In nibh erat, tempor vel augue id, aliquet lacinia massa. Proin mattis luctus ipsum at interdum. Integer hendrerit lectus sit amet tempus commodo. Cras vel quam lectus. Aenean molestie sagittis nulla, dapibus varius ligula varius at. Donec nisl erat, lobortis quis mi in, faucibus lobortis nisi. Duis sollicitudin felis vitae elit venenatis pharetra. Nulla sed bibendum ante. Nunc eget magna at tortor blandit fringilla sed id leo. Maecenas tristique ut arcu lobortis iaculis. Suspendisse tincidunt nulla nec feugiat viverra. Etiam vulputate nulla eget mollis ornare. Nullam blandit, nisi id rhoncus laoreet, urna libero cursus nisl, et aliquam dolor est sit amet neque. Nam mattis ex accumsan augue molestie, sit amet malesuada ante ornare. Praesent id sem tristique libero tincidunt dictum. Vivamus nunc orci, consectetur vel ipsum in, posuere commodo purus. Aenean fringilla sodales magna. Aliquam tempus est non purus faucibus elementum. Ut dui arcu, pulvinar sit amet orci ac, ultrices dictum ligula. Donec nunc nibh, venenatis quis euismod eu, malesuada in nisi. Aliquam in dolor feugiat, sagittis lorem eu, tincidunt ex. In augue libero, sodales a purus quis, sodales ornare quam. Sed at accumsan augue. In tincidunt convallis porta. Suspendisse lobortis gravida tellus non tristique. Duis molestie a velit a tincidunt. Fusce feugiat imperdiet mollis. Vivamus rutrum congue est, nec consequat lorem congue eu.",
                "date": 1480167949,
                "menuId": 13,
                "comments": [],
                "tags": [
                    "Algebra",
                    "Analiza",
                    "Całki"
                ]
            },
            {
                "id": 4,
                "kind": "T",
                "title": "Gwendolyn Owen",
                "intro": "Proident occaecat proident aliquip ut pariatur esse veniam. Enim mollit adipisicing excepteur minim aliqua sint aliquip quis consectetur elit non voluptate. Nisi reprehenderit duis Lorem eiusmod enim adipisicing deserunt consequat consectetur quis. Do non laboris consectetur Lorem quis pariatur nostrud nulla esse ut consequat. Ad minim occaecat irure cillum dolore aliqua cillum nostrud ut. Nostrud in aliqua exercitation veniam velit aute dolor sunt mollit eu dolor consectetur.\r\n",
                "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum libero nibh, et tincidunt lacus malesuada eget. Duis lacus lacus, luctus quis tellus at, viverra condimentum sem. Sed non tellus dolor. Maecenas volutpat scelerisque augue, ac scelerisque eros convallis vitae. Donec volutpat lorem quis commodo fringilla. Cras pretium eu nisl non pellentesque. Cras vehicula nibh diam, vel pharetra lorem eleifend sed. Suspendisse egestas placerat urna, eu tristique arcu consequat sit amet. Sed rutrum sed leo sed condimentum. Proin ultricies nisl arcu, quis blandit nunc gravida a. Phasellus posuere vestibulum aliquet. Donec nec elit id velit tincidunt pharetra nec in dolor. In pretium non risus at lacinia. Morbi ornare hendrerit quam, at ultrices libero aliquet quis. Nunc bibendum facilisis metus, sit amet scelerisque ipsum blandit a. In interdum lorem sed dui pharetra, sit amet finibus nulla pulvinar. Duis eget diam lorem. Fusce blandit ipsum ac mollis gravida. Suspendisse ante ante, ornare in rutrum a, hendrerit ut nunc. In sollicitudin et elit ut ultrices. Proin blandit enim suscipit pretium eleifend. Sed scelerisque tellus feugiat mi elementum consectetur. Phasellus quis nunc pharetra, tempus dolor in, vehicula odio. Pellentesque justo elit, varius vel turpis sit amet, laoreet elementum lectus. Nunc convallis, lacus non suscipit congue, dolor nunc iaculis nulla, a molestie nisi libero in urna. Donec arcu ligula, aliquam vitae accumsan ut, cursus ac erat. Maecenas at erat sodales nunc finibus suscipit ut sit amet nisl. Praesent nec ultricies felis. Pellentesque tempor auctor urna sit amet varius. Vestibulum molestie luctus risus non tempor. Quisque nec eros leo. Curabitur pharetra ligula in consectetur pretium. Curabitur pellentesque gravida magna, a vulputate dolor. Fusce quis aliquet mi. Donec hendrerit pellentesque ipsum nec lacinia. Morbi at ultricies augue. Donec rutrum leo sit amet purus blandit ullamcorper. Nam augue lectus, vestibulum sollicitudin suscipit quis, maximus non enim. Curabitur molestie interdum enim in semper. In malesuada lorem ut neque cursus, sit amet consectetur ante vehicula. Nunc hendrerit lorem eget scelerisque suscipit. Etiam tempus nisi sed aliquet venenatis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi nibh libero, ullamcorper at sodales ac, posuere et ipsum. Nunc dictum egestas mattis. Nunc in sollicitudin libero. In hac habitasse platea dictumst. Sed nec tristique augue. Ut nec blandit risus. Vestibulum vitae magna condimentum nunc ultrices molestie non vel velit. Duis bibendum facilisis nisl bibendum lacinia. Quisque maximus nulla quis massa feugiat, vitae auctor mi posuere. In eu justo eget odio fringilla sagittis. Duis luctus eget lorem nec lacinia. Proin eu dapibus orci, id faucibus ante. Aliquam bibendum magna ut libero laoreet interdum. Sed leo est, hendrerit vel nibh nec, pharetra convallis elit. In nibh erat, tempor vel augue id, aliquet lacinia massa. Proin mattis luctus ipsum at interdum. Integer hendrerit lectus sit amet tempus commodo. Cras vel quam lectus. Aenean molestie sagittis nulla, dapibus varius ligula varius at. Donec nisl erat, lobortis quis mi in, faucibus lobortis nisi. Duis sollicitudin felis vitae elit venenatis pharetra. Nulla sed bibendum ante. Nunc eget magna at tortor blandit fringilla sed id leo. Maecenas tristique ut arcu lobortis iaculis. Suspendisse tincidunt nulla nec feugiat viverra. Etiam vulputate nulla eget mollis ornare. Nullam blandit, nisi id rhoncus laoreet, urna libero cursus nisl, et aliquam dolor est sit amet neque. Nam mattis ex accumsan augue molestie, sit amet malesuada ante ornare. Praesent id sem tristique libero tincidunt dictum. Vivamus nunc orci, consectetur vel ipsum in, posuere commodo purus. Aenean fringilla sodales magna. Aliquam tempus est non purus faucibus elementum. Ut dui arcu, pulvinar sit amet orci ac, ultrices dictum ligula. Donec nunc nibh, venenatis quis euismod eu, malesuada in nisi. Aliquam in dolor feugiat, sagittis lorem eu, tincidunt ex. In augue libero, sodales a purus quis, sodales ornare quam. Sed at accumsan augue. In tincidunt convallis porta. Suspendisse lobortis gravida tellus non tristique. Duis molestie a velit a tincidunt. Fusce feugiat imperdiet mollis. Vivamus rutrum congue est, nec consequat lorem congue eu.",
                "date": 1480167949,
                "menuId": 14,
                "comments": [],
                "tags": [
                    "Funkcje",
                    "Kombinatoryka"
                ]
            },
            {
                "id": 5,
                "kind": "T",
                "title": "Puckett Byrd",
                "intro": "Duis voluptate excepteur elit non cillum qui veniam ullamco ut elit veniam mollit fugiat eu. Aute ipsum ut incididunt duis laborum reprehenderit ullamco deserunt in voluptate aute. Consectetur et mollit cupidatat dolor tempor magna.\r\n",
                "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum libero nibh, et tincidunt lacus malesuada eget. Duis lacus lacus, luctus quis tellus at, viverra condimentum sem. Sed non tellus dolor. Maecenas volutpat scelerisque augue, ac scelerisque eros convallis vitae. Donec volutpat lorem quis commodo fringilla. Cras pretium eu nisl non pellentesque. Cras vehicula nibh diam, vel pharetra lorem eleifend sed. Suspendisse egestas placerat urna, eu tristique arcu consequat sit amet. Sed rutrum sed leo sed condimentum. Proin ultricies nisl arcu, quis blandit nunc gravida a. Phasellus posuere vestibulum aliquet. Donec nec elit id velit tincidunt pharetra nec in dolor. In pretium non risus at lacinia. Morbi ornare hendrerit quam, at ultrices libero aliquet quis. Nunc bibendum facilisis metus, sit amet scelerisque ipsum blandit a. In interdum lorem sed dui pharetra, sit amet finibus nulla pulvinar. Duis eget diam lorem. Fusce blandit ipsum ac mollis gravida. Suspendisse ante ante, ornare in rutrum a, hendrerit ut nunc. In sollicitudin et elit ut ultrices. Proin blandit enim suscipit pretium eleifend. Sed scelerisque tellus feugiat mi elementum consectetur. Phasellus quis nunc pharetra, tempus dolor in, vehicula odio. Pellentesque justo elit, varius vel turpis sit amet, laoreet elementum lectus. Nunc convallis, lacus non suscipit congue, dolor nunc iaculis nulla, a molestie nisi libero in urna. Donec arcu ligula, aliquam vitae accumsan ut, cursus ac erat. Maecenas at erat sodales nunc finibus suscipit ut sit amet nisl. Praesent nec ultricies felis. Pellentesque tempor auctor urna sit amet varius. Vestibulum molestie luctus risus non tempor. Quisque nec eros leo. Curabitur pharetra ligula in consectetur pretium. Curabitur pellentesque gravida magna, a vulputate dolor. Fusce quis aliquet mi. Donec hendrerit pellentesque ipsum nec lacinia. Morbi at ultricies augue. Donec rutrum leo sit amet purus blandit ullamcorper. Nam augue lectus, vestibulum sollicitudin suscipit quis, maximus non enim. Curabitur molestie interdum enim in semper. In malesuada lorem ut neque cursus, sit amet consectetur ante vehicula. Nunc hendrerit lorem eget scelerisque suscipit. Etiam tempus nisi sed aliquet venenatis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi nibh libero, ullamcorper at sodales ac, posuere et ipsum. Nunc dictum egestas mattis. Nunc in sollicitudin libero. In hac habitasse platea dictumst. Sed nec tristique augue. Ut nec blandit risus. Vestibulum vitae magna condimentum nunc ultrices molestie non vel velit. Duis bibendum facilisis nisl bibendum lacinia. Quisque maximus nulla quis massa feugiat, vitae auctor mi posuere. In eu justo eget odio fringilla sagittis. Duis luctus eget lorem nec lacinia. Proin eu dapibus orci, id faucibus ante. Aliquam bibendum magna ut libero laoreet interdum. Sed leo est, hendrerit vel nibh nec, pharetra convallis elit. In nibh erat, tempor vel augue id, aliquet lacinia massa. Proin mattis luctus ipsum at interdum. Integer hendrerit lectus sit amet tempus commodo. Cras vel quam lectus. Aenean molestie sagittis nulla, dapibus varius ligula varius at. Donec nisl erat, lobortis quis mi in, faucibus lobortis nisi. Duis sollicitudin felis vitae elit venenatis pharetra. Nulla sed bibendum ante. Nunc eget magna at tortor blandit fringilla sed id leo. Maecenas tristique ut arcu lobortis iaculis. Suspendisse tincidunt nulla nec feugiat viverra. Etiam vulputate nulla eget mollis ornare. Nullam blandit, nisi id rhoncus laoreet, urna libero cursus nisl, et aliquam dolor est sit amet neque. Nam mattis ex accumsan augue molestie, sit amet malesuada ante ornare. Praesent id sem tristique libero tincidunt dictum. Vivamus nunc orci, consectetur vel ipsum in, posuere commodo purus. Aenean fringilla sodales magna. Aliquam tempus est non purus faucibus elementum. Ut dui arcu, pulvinar sit amet orci ac, ultrices dictum ligula. Donec nunc nibh, venenatis quis euismod eu, malesuada in nisi. Aliquam in dolor feugiat, sagittis lorem eu, tincidunt ex. In augue libero, sodales a purus quis, sodales ornare quam. Sed at accumsan augue. In tincidunt convallis porta. Suspendisse lobortis gravida tellus non tristique. Duis molestie a velit a tincidunt. Fusce feugiat imperdiet mollis. Vivamus rutrum congue est, nec consequat lorem congue eu.",
                "date": 1480167949,
                "menuId": 15,
                "comments": [
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    },
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    },
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    }
                ],
                "tags": [

                ]
            },
            {
                "id": 6,
                "kind": "Z",
                "title": "Stanley Herman",
                "intro": "Ex eu cillum aute ad Lorem labore culpa ipsum eiusmod nostrud. Mollit excepteur anim magna eu nostrud sunt sit proident. Ea id aliquip esse veniam non officia irure aute ad duis occaecat fugiat. Cupidatat excepteur excepteur labore qui anim. Magna culpa exercitation mollit duis nostrud ex duis.\r\n",
                "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec bibendum libero nibh, et tincidunt lacus malesuada eget. Duis lacus lacus, luctus quis tellus at, viverra condimentum sem. Sed non tellus dolor. Maecenas volutpat scelerisque augue, ac scelerisque eros convallis vitae. Donec volutpat lorem quis commodo fringilla. Cras pretium eu nisl non pellentesque. Cras vehicula nibh diam, vel pharetra lorem eleifend sed. Suspendisse egestas placerat urna, eu tristique arcu consequat sit amet. Sed rutrum sed leo sed condimentum. Proin ultricies nisl arcu, quis blandit nunc gravida a. Phasellus posuere vestibulum aliquet. Donec nec elit id velit tincidunt pharetra nec in dolor. In pretium non risus at lacinia. Morbi ornare hendrerit quam, at ultrices libero aliquet quis. Nunc bibendum facilisis metus, sit amet scelerisque ipsum blandit a. In interdum lorem sed dui pharetra, sit amet finibus nulla pulvinar. Duis eget diam lorem. Fusce blandit ipsum ac mollis gravida. Suspendisse ante ante, ornare in rutrum a, hendrerit ut nunc. In sollicitudin et elit ut ultrices. Proin blandit enim suscipit pretium eleifend. Sed scelerisque tellus feugiat mi elementum consectetur. Phasellus quis nunc pharetra, tempus dolor in, vehicula odio. Pellentesque justo elit, varius vel turpis sit amet, laoreet elementum lectus. Nunc convallis, lacus non suscipit congue, dolor nunc iaculis nulla, a molestie nisi libero in urna. Donec arcu ligula, aliquam vitae accumsan ut, cursus ac erat. Maecenas at erat sodales nunc finibus suscipit ut sit amet nisl. Praesent nec ultricies felis. Pellentesque tempor auctor urna sit amet varius. Vestibulum molestie luctus risus non tempor. Quisque nec eros leo. Curabitur pharetra ligula in consectetur pretium. Curabitur pellentesque gravida magna, a vulputate dolor. Fusce quis aliquet mi. Donec hendrerit pellentesque ipsum nec lacinia. Morbi at ultricies augue. Donec rutrum leo sit amet purus blandit ullamcorper. Nam augue lectus, vestibulum sollicitudin suscipit quis, maximus non enim. Curabitur molestie interdum enim in semper. In malesuada lorem ut neque cursus, sit amet consectetur ante vehicula. Nunc hendrerit lorem eget scelerisque suscipit. Etiam tempus nisi sed aliquet venenatis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi nibh libero, ullamcorper at sodales ac, posuere et ipsum. Nunc dictum egestas mattis. Nunc in sollicitudin libero. In hac habitasse platea dictumst. Sed nec tristique augue. Ut nec blandit risus. Vestibulum vitae magna condimentum nunc ultrices molestie non vel velit. Duis bibendum facilisis nisl bibendum lacinia. Quisque maximus nulla quis massa feugiat, vitae auctor mi posuere. In eu justo eget odio fringilla sagittis. Duis luctus eget lorem nec lacinia. Proin eu dapibus orci, id faucibus ante. Aliquam bibendum magna ut libero laoreet interdum. Sed leo est, hendrerit vel nibh nec, pharetra convallis elit. In nibh erat, tempor vel augue id, aliquet lacinia massa. Proin mattis luctus ipsum at interdum. Integer hendrerit lectus sit amet tempus commodo. Cras vel quam lectus. Aenean molestie sagittis nulla, dapibus varius ligula varius at. Donec nisl erat, lobortis quis mi in, faucibus lobortis nisi. Duis sollicitudin felis vitae elit venenatis pharetra. Nulla sed bibendum ante. Nunc eget magna at tortor blandit fringilla sed id leo. Maecenas tristique ut arcu lobortis iaculis. Suspendisse tincidunt nulla nec feugiat viverra. Etiam vulputate nulla eget mollis ornare. Nullam blandit, nisi id rhoncus laoreet, urna libero cursus nisl, et aliquam dolor est sit amet neque. Nam mattis ex accumsan augue molestie, sit amet malesuada ante ornare. Praesent id sem tristique libero tincidunt dictum. Vivamus nunc orci, consectetur vel ipsum in, posuere commodo purus. Aenean fringilla sodales magna. Aliquam tempus est non purus faucibus elementum. Ut dui arcu, pulvinar sit amet orci ac, ultrices dictum ligula. Donec nunc nibh, venenatis quis euismod eu, malesuada in nisi. Aliquam in dolor feugiat, sagittis lorem eu, tincidunt ex. In augue libero, sodales a purus quis, sodales ornare quam. Sed at accumsan augue. In tincidunt convallis porta. Suspendisse lobortis gravida tellus non tristique. Duis molestie a velit a tincidunt. Fusce feugiat imperdiet mollis. Vivamus rutrum congue est, nec consequat lorem congue eu.",
                "date": 1480167949,
                "menuId": 15,
                "comments": [
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    },
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    },
                    {
                        "author": "maxiu",
                        "date": "1480167949",
                        "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sem lacus, cursus id auctor et, viverra eu ipsum. Suspendisse congue et arcu eu efficitur. Pellentesque fringilla odio non erat molestie feugiat. Nulla eu dolor et mi malesuada mollis. Donec non volutpat libero. Nam ex dui, maximus at justo blandit, finibus malesuada risus. Morbi in metus id tellus elementum efficitur. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla pellentesque auctor dolor ac ullamcorper. Aenean suscipit leo et lectus finibus vehicula. Maecenas sed lobortis lorem."
                    }
                ],
                "tags": [
                    "Pitagoras"
                ]
            }
        ])

    .run(function ($httpBackend, jpartfilter, mockMenu, mockTags, mockArticle, mockArticles, mockStorage) {
//        $httpBackend.whenGET('/menu').respond(mockMenu);
//        $httpBackend.whenGET('/tags').respond(mockTags);
        $httpBackend.whenGET('/articles').respond(mockArticles);
        $httpBackend.whenGET('/storage').respond(mockStorage);
        $httpBackend.whenPOST('/login').respond(true);
        $httpBackend.whenPOST('/logout').respond(false);

        $httpBackend.whenGET(/\/storage\/(.+)/).respond(function(method, url) {
            var url_parts = url.split('/');
            var id = parseInt(url_parts[url_parts.length - 1], 10);

            if (isNaN(id))
                throw new Error("HTTP request");

            return [200, jpartfilter(mockStorage, 'id', [id])[0]];
        });

        $httpBackend.whenGET(/\/article\/id\/(.+)/).respond(function(method, url) {
            var url_parts = url.split('/');
            var id = parseInt(url_parts[url_parts.length - 1], 10);

            if (isNaN(id))
                throw new Error("HTTP request");

            return [200, jpartfilter(mockArticle, 'id', [id])[0]];
        });

        $httpBackend.whenGET(/\/articles\/menuid\/(.+)/).respond(function(method, url) {
            var url_parts = url.split('/');
            var id = parseInt(url_parts[url_parts.length - 1], 10);

            if (isNaN(id))
                return [200, mockArticles];

            return [200, jpartfilter(mockArticles, 'menuId', [id])];
        });

        $httpBackend.whenGET(new RegExp('.*')).passThrough();
    });