


<!DOCTYPE html>
<html>
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# githubog: http://ogp.me/ns/fb/githubog#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Honours-Marks-Database/sql/Honours_schema.sql at orm · jon2512chua/Honours-Marks-Database</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png" />
    <link rel="logo" type="image/svg" href="https://github-media-downloads.s3.amazonaws.com/github-logo.svg" />
    <meta property="og:image" content="https://github.global.ssl.fastly.net/images/modules/logos_page/Octocat.png">
    <meta name="hostname" content="github-fe134-cp1-prd.iad.github.net">
    <meta name="ruby" content="ruby 1.9.3p194-tcs-github-tcmalloc (2012-05-25, TCS patched 2012-05-27, GitHub v1.0.36) [x86_64-linux]">
    <link rel="assets" href="https://github.global.ssl.fastly.net/">
    <link rel="conduit-xhr" href="https://ghconduit.com:25035/">
    <link rel="xhr-socket" href="/_sockets" />
    


    <meta name="msapplication-TileImage" content="/windows-tile.png" />
    <meta name="msapplication-TileColor" content="#ffffff" />
    <meta name="selected-link" value="repo_source" data-pjax-transient />
    <meta content="collector.githubapp.com" name="octolytics-host" /><meta content="github" name="octolytics-app-id" /><meta content="825F87A8:56EE:147DEC5:5247AC6B" name="octolytics-dimension-request_id" /><meta content="5210074" name="octolytics-actor-id" /><meta content="20522805" name="octolytics-actor-login" /><meta content="8b3a9fca22f08ebd33de0b09b9f98a66712395524721ef34e79295ec02d724f6" name="octolytics-actor-hash" />
    

    
    
    <link rel="icon" type="image/x-icon" href="/favicon.ico" />

    <meta content="authenticity_token" name="csrf-param" />
<meta content="SNnUNGy7zENm/jHsEGx2fWXE+zAQPBPpVe6RQ36TvUE=" name="csrf-token" />

    <link href="https://github.global.ssl.fastly.net/assets/github-4288f026700410ae032b5d324dea2b4571789d7c.css" media="all" rel="stylesheet" type="text/css" />
    <link href="https://github.global.ssl.fastly.net/assets/github2-88d5087029dbe346f413843c4cb0149921840ef5.css" media="all" rel="stylesheet" type="text/css" />
    

    

      <script src="https://github.global.ssl.fastly.net/assets/frameworks-4e5aeedcc7a86dcff8294cb84644a333b46202a2.js" type="text/javascript"></script>
      <script src="https://github.global.ssl.fastly.net/assets/github-3eca66e8d84a2ff6e7aa623a998827892eceb472.js" type="text/javascript"></script>
      
      <meta http-equiv="x-pjax-version" content="405a8aa4027641752499b79ebf1cb5a3">

        <link data-pjax-transient rel='permalink' href='/jon2512chua/Honours-Marks-Database/blob/18c53395fbb03d146d5943bd22e941466dd39581/sql/Honours_schema.sql'>
  <meta property="og:title" content="Honours-Marks-Database"/>
  <meta property="og:type" content="githubog:gitrepository"/>
  <meta property="og:url" content="https://github.com/jon2512chua/Honours-Marks-Database"/>
  <meta property="og:image" content="https://github.global.ssl.fastly.net/images/gravatars/gravatar-user-420.png"/>
  <meta property="og:site_name" content="GitHub"/>
  <meta property="og:description" content="Contribute to Honours-Marks-Database development by creating an account on GitHub."/>

  <meta name="description" content="Contribute to Honours-Marks-Database development by creating an account on GitHub." />

  <meta content="435095" name="octolytics-dimension-user_id" /><meta content="jon2512chua" name="octolytics-dimension-user_login" /><meta content="12719075" name="octolytics-dimension-repository_id" /><meta content="jon2512chua/Honours-Marks-Database" name="octolytics-dimension-repository_nwo" /><meta content="true" name="octolytics-dimension-repository_public" /><meta content="false" name="octolytics-dimension-repository_is_fork" /><meta content="12719075" name="octolytics-dimension-repository_network_root_id" /><meta content="jon2512chua/Honours-Marks-Database" name="octolytics-dimension-repository_network_root_nwo" />
  <link href="https://github.com/jon2512chua/Honours-Marks-Database/commits/orm.atom" rel="alternate" title="Recent Commits to Honours-Marks-Database:orm" type="application/atom+xml" />

  </head>


  <body class="logged_in  env-production macintosh vis-public  page-blob">
    <div class="wrapper">
      
      
      


      <div class="header header-logged-in true">
  <div class="container clearfix">

    <a class="header-logo-invertocat" href="https://github.com/">
  <span class="mega-octicon octicon-mark-github"></span>
</a>

    
    <a href="/jon2512chua/Honours-Marks-Database/notifications" class="notification-indicator tooltipped downwards contextually-unread" data-gotokey="n" title="You have unread notifications in this repository">
        <span class="mail-status unread"></span>
</a>

      <div class="command-bar js-command-bar  in-repository">
          <form accept-charset="UTF-8" action="/search" class="command-bar-form" id="top_search_form" method="get">

<input type="text" data-hotkey="/ s" name="q" id="js-command-bar-field" placeholder="Search or type a command" tabindex="1" autocapitalize="off"
    
    data-username="20522805"
      data-repo="jon2512chua/Honours-Marks-Database"
      data-branch="orm"
      data-sha="1d72509fe338c95b3f7db9ad2ce567195a81d04d"
  >

    <input type="hidden" name="nwo" value="jon2512chua/Honours-Marks-Database" />

    <div class="select-menu js-menu-container js-select-menu search-context-select-menu">
      <span class="minibutton select-menu-button js-menu-target">
        <span class="js-select-button">This repository</span>
      </span>

      <div class="select-menu-modal-holder js-menu-content js-navigation-container">
        <div class="select-menu-modal">

          <div class="select-menu-item js-navigation-item js-this-repository-navigation-item selected">
            <span class="select-menu-item-icon octicon octicon-check"></span>
            <input type="radio" class="js-search-this-repository" name="search_target" value="repository" checked="checked" />
            <div class="select-menu-item-text js-select-button-text">This repository</div>
          </div> <!-- /.select-menu-item -->

          <div class="select-menu-item js-navigation-item js-all-repositories-navigation-item">
            <span class="select-menu-item-icon octicon octicon-check"></span>
            <input type="radio" name="search_target" value="global" />
            <div class="select-menu-item-text js-select-button-text">All repositories</div>
          </div> <!-- /.select-menu-item -->

        </div>
      </div>
    </div>

  <span class="octicon help tooltipped downwards" title="Show command bar help">
    <span class="octicon octicon-question"></span>
  </span>


  <input type="hidden" name="ref" value="cmdform">

</form>
        <ul class="top-nav">
          <li class="explore"><a href="/explore">Explore</a></li>
            <li><a href="https://gist.github.com">Gist</a></li>
            <li><a href="/blog">Blog</a></li>
          <li><a href="https://help.github.com">Help</a></li>
        </ul>
      </div>

    


  <ul id="user-links">
    <li>
      <a href="/20522805" class="name">
        <img height="20" src="https://0.gravatar.com/avatar/00907ca3234113218842d0cc16a6ead9?d=https%3A%2F%2Fidenticons.github.com%2Fd6522c5a9b9aee479a6de33288c76a02.png&amp;s=140" width="20" /> 20522805
      </a>
    </li>

      <li>
        <a href="/new" id="new_repo" class="tooltipped downwards" title="Create a new repo" aria-label="Create a new repo">
          <span class="octicon octicon-repo-create"></span>
        </a>
      </li>

      <li>
        <a href="/settings/profile" id="account_settings"
          class="tooltipped downwards"
          aria-label="Account settings "
          title="Account settings ">
          <span class="octicon octicon-tools"></span>
        </a>
      </li>
      <li>
        <a class="tooltipped downwards" href="/logout" data-method="post" id="logout" title="Sign out" aria-label="Sign out">
          <span class="octicon octicon-log-out"></span>
        </a>
      </li>

  </ul>

<div class="js-new-dropdown-contents hidden">
  

<ul class="dropdown-menu">
  <li>
    <a href="/new"><span class="octicon octicon-repo-create"></span> New repository</a>
  </li>
  <li>
    <a href="/organizations/new"><span class="octicon octicon-organization"></span> New organization</a>
  </li>



    <li class="section-title">
      <span title="jon2512chua/Honours-Marks-Database">This repository</span>
    </li>
    <li>
      <a href="/jon2512chua/Honours-Marks-Database/issues/new"><span class="octicon octicon-issue-opened"></span> New issue</a>
    </li>
</ul>

</div>


    
  </div>
</div>

      

      




          <div class="site" itemscope itemtype="http://schema.org/WebPage">
    
    <div class="pagehead repohead instapaper_ignore readability-menu">
      <div class="container">
        

<ul class="pagehead-actions">

    <li class="subscription">
      <form accept-charset="UTF-8" action="/notifications/subscribe" class="js-social-container" data-autosubmit="true" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="authenticity_token" type="hidden" value="SNnUNGy7zENm/jHsEGx2fWXE+zAQPBPpVe6RQ36TvUE=" /></div>  <input id="repository_id" name="repository_id" type="hidden" value="12719075" />

    <div class="select-menu js-menu-container js-select-menu">
        <a class="social-count js-social-count" href="/jon2512chua/Honours-Marks-Database/watchers">
          4
        </a>
      <span class="minibutton select-menu-button with-count js-menu-target" role="button" tabindex="0">
        <span class="js-select-button">
          <span class="octicon octicon-eye-unwatch"></span>
          Unwatch
        </span>
      </span>

      <div class="select-menu-modal-holder">
        <div class="select-menu-modal subscription-menu-modal js-menu-content">
          <div class="select-menu-header">
            <span class="select-menu-title">Notification status</span>
            <span class="octicon octicon-remove-close js-menu-close"></span>
          </div> <!-- /.select-menu-header -->

          <div class="select-menu-list js-navigation-container" role="menu">

            <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input id="do_included" name="do" type="radio" value="included" />
                <h4>Not watching</h4>
                <span class="description">You only receive notifications for discussions in which you participate or are @mentioned.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-eye-watch"></span>
                  Watch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item selected" role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input checked="checked" id="do_subscribed" name="do" type="radio" value="subscribed" />
                <h4>Watching</h4>
                <span class="description">You receive notifications for all discussions in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-eye-unwatch"></span>
                  Unwatch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input id="do_ignore" name="do" type="radio" value="ignore" />
                <h4>Ignoring</h4>
                <span class="description">You do not receive any notifications for discussions in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-mute"></span>
                  Stop ignoring
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

          </div> <!-- /.select-menu-list -->

        </div> <!-- /.select-menu-modal -->
      </div> <!-- /.select-menu-modal-holder -->
    </div> <!-- /.select-menu -->

</form>
    </li>

  <li>
  
<div class="js-toggler-container js-social-container starring-container ">
  <a href="/jon2512chua/Honours-Marks-Database/unstar" class="minibutton with-count js-toggler-target star-button starred upwards" title="Unstar this repo" data-remote="true" data-method="post" rel="nofollow">
    <span class="octicon octicon-star-delete"></span><span class="text">Unstar</span>
  </a>
  <a href="/jon2512chua/Honours-Marks-Database/star" class="minibutton with-count js-toggler-target star-button unstarred upwards" title="Star this repo" data-remote="true" data-method="post" rel="nofollow">
    <span class="octicon octicon-star"></span><span class="text">Star</span>
  </a>
  <a class="social-count js-social-count" href="/jon2512chua/Honours-Marks-Database/stargazers">0</a>
</div>

  </li>


        <li>
          <a href="/jon2512chua/Honours-Marks-Database/fork" class="minibutton with-count js-toggler-target fork-button lighter upwards" title="Fork this repo" rel="nofollow" data-method="post">
            <span class="octicon octicon-git-branch-create"></span><span class="text">Fork</span>
          </a>
          <a href="/jon2512chua/Honours-Marks-Database/network" class="social-count">0</a>
        </li>


</ul>

        <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public">
          <span class="repo-label"><span>public</span></span>
          <span class="mega-octicon octicon-repo"></span>
          <span class="author">
            <a href="/jon2512chua" class="url fn" itemprop="url" rel="author"><span itemprop="title">jon2512chua</span></a></span
          ><span class="repohead-name-divider">/</span><strong
          ><a href="/jon2512chua/Honours-Marks-Database" class="js-current-repository js-repo-home-link">Honours-Marks-Database</a></strong>

          <span class="page-context-loader">
            <img alt="Octocat-spinner-32" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
          </span>

        </h1>
      </div><!-- /.container -->
    </div><!-- /.repohead -->

    <div class="container">

      <div class="repository-with-sidebar repo-container ">

        <div class="repository-sidebar">
            

<div class="repo-nav repo-nav-full js-repository-container-pjax js-octicon-loaders">
  <div class="repo-nav-contents">
    <ul class="repo-menu">
      <li class="tooltipped leftwards" title="Code">
        <a href="/jon2512chua/Honours-Marks-Database/tree/orm" aria-label="Code" class="js-selected-navigation-item selected" data-gotokey="c" data-pjax="true" data-selected-links="repo_source repo_downloads repo_commits repo_tags repo_branches /jon2512chua/Honours-Marks-Database/tree/orm">
          <span class="octicon octicon-code"></span> <span class="full-word">Code</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

        <li class="tooltipped leftwards" title="Issues">
          <a href="/jon2512chua/Honours-Marks-Database/issues" aria-label="Issues" class="js-selected-navigation-item js-disable-pjax" data-gotokey="i" data-selected-links="repo_issues /jon2512chua/Honours-Marks-Database/issues">
            <span class="octicon octicon-issue-opened"></span> <span class="full-word">Issues</span>
            <span class='counter'>0</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>        </li>

      <li class="tooltipped leftwards" title="Pull Requests"><a href="/jon2512chua/Honours-Marks-Database/pulls" aria-label="Pull Requests" class="js-selected-navigation-item js-disable-pjax" data-gotokey="p" data-selected-links="repo_pulls /jon2512chua/Honours-Marks-Database/pulls">
            <span class="octicon octicon-git-pull-request"></span> <span class="full-word">Pull Requests</span>
            <span class='counter'>0</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>


        <li class="tooltipped leftwards" title="Wiki">
          <a href="/jon2512chua/Honours-Marks-Database/wiki" aria-label="Wiki" class="js-selected-navigation-item " data-pjax="true" data-selected-links="repo_wiki /jon2512chua/Honours-Marks-Database/wiki">
            <span class="octicon octicon-book"></span> <span class="full-word">Wiki</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>        </li>
    </ul>
    <div class="repo-menu-separator"></div>
    <ul class="repo-menu">

      <li class="tooltipped leftwards" title="Pulse">
        <a href="/jon2512chua/Honours-Marks-Database/pulse" aria-label="Pulse" class="js-selected-navigation-item " data-pjax="true" data-selected-links="pulse /jon2512chua/Honours-Marks-Database/pulse">
          <span class="octicon octicon-pulse"></span> <span class="full-word">Pulse</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

      <li class="tooltipped leftwards" title="Graphs">
        <a href="/jon2512chua/Honours-Marks-Database/graphs" aria-label="Graphs" class="js-selected-navigation-item " data-pjax="true" data-selected-links="repo_graphs repo_contributors /jon2512chua/Honours-Marks-Database/graphs">
          <span class="octicon octicon-graph"></span> <span class="full-word">Graphs</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

      <li class="tooltipped leftwards" title="Network">
        <a href="/jon2512chua/Honours-Marks-Database/network" aria-label="Network" class="js-selected-navigation-item js-disable-pjax" data-selected-links="repo_network /jon2512chua/Honours-Marks-Database/network">
          <span class="octicon octicon-git-branch"></span> <span class="full-word">Network</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>
    </ul>


  </div>
</div>

            <div class="only-with-full-nav">
              

  

<div class="clone-url open"
  data-protocol-type="http"
  data-url="/users/set_protocol?protocol_selector=http&amp;protocol_type=push">
  <h3><strong>HTTPS</strong> clone URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="https://github.com/jon2512chua/Honours-Marks-Database.git" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="https://github.com/jon2512chua/Honours-Marks-Database.git" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>

  

<div class="clone-url "
  data-protocol-type="ssh"
  data-url="/users/set_protocol?protocol_selector=ssh&amp;protocol_type=push">
  <h3><strong>SSH</strong> clone URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="git@github.com:jon2512chua/Honours-Marks-Database.git" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="git@github.com:jon2512chua/Honours-Marks-Database.git" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>

  

<div class="clone-url "
  data-protocol-type="subversion"
  data-url="/users/set_protocol?protocol_selector=subversion&amp;protocol_type=push">
  <h3><strong>Subversion</strong> checkout URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="https://github.com/jon2512chua/Honours-Marks-Database" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="https://github.com/jon2512chua/Honours-Marks-Database" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>


<p class="clone-options">You can clone with
      <a href="#" class="js-clone-selector" data-protocol="http">HTTPS</a>,
      <a href="#" class="js-clone-selector" data-protocol="ssh">SSH</a>,
      or <a href="#" class="js-clone-selector" data-protocol="subversion">Subversion</a>.
  <span class="octicon help tooltipped upwards" title="Get help on which URL is right for you.">
    <a href="https://help.github.com/articles/which-remote-url-should-i-use">
    <span class="octicon octicon-question"></span>
    </a>
  </span>
</p>

  <a href="github-mac://openRepo/https://github.com/jon2512chua/Honours-Marks-Database" data-url="github-mac://openRepo/https://github.com/jon2512chua/Honours-Marks-Database" class="minibutton sidebar-button js-conduit-rewrite-url">
    <span class="octicon octicon-device-desktop"></span>
    Clone in Desktop
  </a>


              <a href="/jon2512chua/Honours-Marks-Database/archive/orm.zip"
                 class="minibutton sidebar-button"
                 title="Download this repository as a zip file"
                 rel="nofollow">
                <span class="octicon octicon-cloud-download"></span>
                Download ZIP
              </a>
            </div>
        </div><!-- /.repository-sidebar -->

        <div id="js-repo-pjax-container" class="repository-content context-loader-container" data-pjax-container>
          


<!-- blob contrib key: blob_contributors:v21:4d902d1a94d38168f88055463b801502 -->

<p title="This is a placeholder element" class="js-history-link-replace hidden"></p>

<a href="/jon2512chua/Honours-Marks-Database/find/orm" data-pjax data-hotkey="t" class="js-show-file-finder" style="display:none">Show File Finder</a>

<div class="file-navigation">
  


<div class="select-menu js-menu-container js-select-menu" >
  <span class="minibutton select-menu-button js-menu-target" data-hotkey="w"
    data-master-branch="master"
    data-ref="orm"
    role="button" aria-label="Switch branches or tags" tabindex="0">
    <span class="octicon octicon-git-branch"></span>
    <i>branch:</i>
    <span class="js-select-button">orm</span>
  </span>

  <div class="select-menu-modal-holder js-menu-content js-navigation-container" data-pjax>

    <div class="select-menu-modal">
      <div class="select-menu-header">
        <span class="select-menu-title">Switch branches/tags</span>
        <span class="octicon octicon-remove-close js-menu-close"></span>
      </div> <!-- /.select-menu-header -->

      <div class="select-menu-filters">
        <div class="select-menu-text-filter">
          <input type="text" aria-label="Find or create a branch…" id="context-commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Find or create a branch…">
        </div>
        <div class="select-menu-tabs">
          <ul>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="branches" class="js-select-menu-tab">Branches</a>
            </li>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="tags" class="js-select-menu-tab">Tags</a>
            </li>
          </ul>
        </div><!-- /.select-menu-tabs -->
      </div><!-- /.select-menu-filters -->

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="branches">

        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/jon2512chua/Honours-Marks-Database/blob/develop/sql/Honours_schema.sql"
                 data-name="develop"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="develop">develop</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/jon2512chua/Honours-Marks-Database/blob/gui/sql/Honours_schema.sql"
                 data-name="gui"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="gui">gui</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/jon2512chua/Honours-Marks-Database/blob/logic/sql/Honours_schema.sql"
                 data-name="logic"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="logic">logic</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/jon2512chua/Honours-Marks-Database/blob/master/sql/Honours_schema.sql"
                 data-name="master"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="master">master</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item selected">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/jon2512chua/Honours-Marks-Database/blob/orm/sql/Honours_schema.sql"
                 data-name="orm"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="orm">orm</a>
            </div> <!-- /.select-menu-item -->
        </div>

          <form accept-charset="UTF-8" action="/jon2512chua/Honours-Marks-Database/branches" class="js-create-branch select-menu-item select-menu-new-item-form js-navigation-item js-new-item-form" method="post"><div style="margin:0;padding:0;display:inline"><input name="authenticity_token" type="hidden" value="SNnUNGy7zENm/jHsEGx2fWXE+zAQPBPpVe6RQ36TvUE=" /></div>
            <span class="octicon octicon-git-branch-create select-menu-item-icon"></span>
            <div class="select-menu-item-text">
              <h4>Create branch: <span class="js-new-item-name"></span></h4>
              <span class="description">from ‘orm’</span>
            </div>
            <input type="hidden" name="name" id="name" class="js-new-item-value">
            <input type="hidden" name="branch" id="branch" value="orm" />
            <input type="hidden" name="path" id="branch" value="sql/Honours_schema.sql" />
          </form> <!-- /.select-menu-item -->

      </div> <!-- /.select-menu-list -->

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="tags">
        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


        </div>

        <div class="select-menu-no-results">Nothing to show</div>
      </div> <!-- /.select-menu-list -->

    </div> <!-- /.select-menu-modal -->
  </div> <!-- /.select-menu-modal-holder -->
</div> <!-- /.select-menu -->

  <div class="breadcrumb">
    <span class='repo-root js-repo-root'><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jon2512chua/Honours-Marks-Database/tree/orm" data-branch="orm" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">Honours-Marks-Database</span></a></span></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jon2512chua/Honours-Marks-Database/tree/orm/sql" data-branch="orm" data-direction="back" data-pjax="true" itemscope="url"><span itemprop="title">sql</span></a></span><span class="separator"> / </span><strong class="final-path">Honours_schema.sql</strong> <span class="js-zeroclipboard minibutton zeroclipboard-button" data-clipboard-text="sql/Honours_schema.sql" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>


  <div class="commit commit-loader file-history-tease js-deferred-content" data-url="/jon2512chua/Honours-Marks-Database/contributors/orm/sql/Honours_schema.sql">
    Fetching contributors…

    <div class="participation">
      <p class="loader-loading"><img alt="Octocat-spinner-32-eaf2f5" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32-EAF2F5.gif" width="16" /></p>
      <p class="loader-error">Cannot retrieve contributors at this time</p>
    </div>
  </div>

<div id="files" class="bubble">
  <div class="file">
    <div class="meta">
      <div class="info">
        <span class="icon"><b class="octicon octicon-file-text"></b></span>
        <span class="mode" title="File Mode">file</span>
          <span>130 lines (111 sloc)</span>
        <span>3.178 kb</span>
      </div>
      <div class="actions">
        <div class="button-group">
                <a class="minibutton"
                   href="/jon2512chua/Honours-Marks-Database/edit/orm/sql/Honours_schema.sql"
                   data-method="post" rel="nofollow" data-hotkey="e">Edit</a>
          <a href="/jon2512chua/Honours-Marks-Database/raw/orm/sql/Honours_schema.sql" class="button minibutton " id="raw-url">Raw</a>
            <a href="/jon2512chua/Honours-Marks-Database/blame/orm/sql/Honours_schema.sql" class="button minibutton ">Blame</a>
          <a href="/jon2512chua/Honours-Marks-Database/commits/orm/sql/Honours_schema.sql" class="button minibutton " rel="nofollow">History</a>
        </div><!-- /.button-group -->
            <a class="minibutton danger empty-icon tooltipped downwards"
               href="/jon2512chua/Honours-Marks-Database/delete/orm/sql/Honours_schema.sql"
               title=""
               data-method="post" data-test-id="delete-blob-file" rel="nofollow">
            Delete
          </a>
      </div><!-- /.actions -->

    </div>
        <div class="blob-wrapper data type-sql js-blob-data">
        <table class="file-code file-diff">
          <tr class="file-code-line">
            <td class="blob-line-nums">
              <span id="L1" rel="#L1">1</span>
<span id="L2" rel="#L2">2</span>
<span id="L3" rel="#L3">3</span>
<span id="L4" rel="#L4">4</span>
<span id="L5" rel="#L5">5</span>
<span id="L6" rel="#L6">6</span>
<span id="L7" rel="#L7">7</span>
<span id="L8" rel="#L8">8</span>
<span id="L9" rel="#L9">9</span>
<span id="L10" rel="#L10">10</span>
<span id="L11" rel="#L11">11</span>
<span id="L12" rel="#L12">12</span>
<span id="L13" rel="#L13">13</span>
<span id="L14" rel="#L14">14</span>
<span id="L15" rel="#L15">15</span>
<span id="L16" rel="#L16">16</span>
<span id="L17" rel="#L17">17</span>
<span id="L18" rel="#L18">18</span>
<span id="L19" rel="#L19">19</span>
<span id="L20" rel="#L20">20</span>
<span id="L21" rel="#L21">21</span>
<span id="L22" rel="#L22">22</span>
<span id="L23" rel="#L23">23</span>
<span id="L24" rel="#L24">24</span>
<span id="L25" rel="#L25">25</span>
<span id="L26" rel="#L26">26</span>
<span id="L27" rel="#L27">27</span>
<span id="L28" rel="#L28">28</span>
<span id="L29" rel="#L29">29</span>
<span id="L30" rel="#L30">30</span>
<span id="L31" rel="#L31">31</span>
<span id="L32" rel="#L32">32</span>
<span id="L33" rel="#L33">33</span>
<span id="L34" rel="#L34">34</span>
<span id="L35" rel="#L35">35</span>
<span id="L36" rel="#L36">36</span>
<span id="L37" rel="#L37">37</span>
<span id="L38" rel="#L38">38</span>
<span id="L39" rel="#L39">39</span>
<span id="L40" rel="#L40">40</span>
<span id="L41" rel="#L41">41</span>
<span id="L42" rel="#L42">42</span>
<span id="L43" rel="#L43">43</span>
<span id="L44" rel="#L44">44</span>
<span id="L45" rel="#L45">45</span>
<span id="L46" rel="#L46">46</span>
<span id="L47" rel="#L47">47</span>
<span id="L48" rel="#L48">48</span>
<span id="L49" rel="#L49">49</span>
<span id="L50" rel="#L50">50</span>
<span id="L51" rel="#L51">51</span>
<span id="L52" rel="#L52">52</span>
<span id="L53" rel="#L53">53</span>
<span id="L54" rel="#L54">54</span>
<span id="L55" rel="#L55">55</span>
<span id="L56" rel="#L56">56</span>
<span id="L57" rel="#L57">57</span>
<span id="L58" rel="#L58">58</span>
<span id="L59" rel="#L59">59</span>
<span id="L60" rel="#L60">60</span>
<span id="L61" rel="#L61">61</span>
<span id="L62" rel="#L62">62</span>
<span id="L63" rel="#L63">63</span>
<span id="L64" rel="#L64">64</span>
<span id="L65" rel="#L65">65</span>
<span id="L66" rel="#L66">66</span>
<span id="L67" rel="#L67">67</span>
<span id="L68" rel="#L68">68</span>
<span id="L69" rel="#L69">69</span>
<span id="L70" rel="#L70">70</span>
<span id="L71" rel="#L71">71</span>
<span id="L72" rel="#L72">72</span>
<span id="L73" rel="#L73">73</span>
<span id="L74" rel="#L74">74</span>
<span id="L75" rel="#L75">75</span>
<span id="L76" rel="#L76">76</span>
<span id="L77" rel="#L77">77</span>
<span id="L78" rel="#L78">78</span>
<span id="L79" rel="#L79">79</span>
<span id="L80" rel="#L80">80</span>
<span id="L81" rel="#L81">81</span>
<span id="L82" rel="#L82">82</span>
<span id="L83" rel="#L83">83</span>
<span id="L84" rel="#L84">84</span>
<span id="L85" rel="#L85">85</span>
<span id="L86" rel="#L86">86</span>
<span id="L87" rel="#L87">87</span>
<span id="L88" rel="#L88">88</span>
<span id="L89" rel="#L89">89</span>
<span id="L90" rel="#L90">90</span>
<span id="L91" rel="#L91">91</span>
<span id="L92" rel="#L92">92</span>
<span id="L93" rel="#L93">93</span>
<span id="L94" rel="#L94">94</span>
<span id="L95" rel="#L95">95</span>
<span id="L96" rel="#L96">96</span>
<span id="L97" rel="#L97">97</span>
<span id="L98" rel="#L98">98</span>
<span id="L99" rel="#L99">99</span>
<span id="L100" rel="#L100">100</span>
<span id="L101" rel="#L101">101</span>
<span id="L102" rel="#L102">102</span>
<span id="L103" rel="#L103">103</span>
<span id="L104" rel="#L104">104</span>
<span id="L105" rel="#L105">105</span>
<span id="L106" rel="#L106">106</span>
<span id="L107" rel="#L107">107</span>
<span id="L108" rel="#L108">108</span>
<span id="L109" rel="#L109">109</span>
<span id="L110" rel="#L110">110</span>
<span id="L111" rel="#L111">111</span>
<span id="L112" rel="#L112">112</span>
<span id="L113" rel="#L113">113</span>
<span id="L114" rel="#L114">114</span>
<span id="L115" rel="#L115">115</span>
<span id="L116" rel="#L116">116</span>
<span id="L117" rel="#L117">117</span>
<span id="L118" rel="#L118">118</span>
<span id="L119" rel="#L119">119</span>
<span id="L120" rel="#L120">120</span>
<span id="L121" rel="#L121">121</span>
<span id="L122" rel="#L122">122</span>
<span id="L123" rel="#L123">123</span>
<span id="L124" rel="#L124">124</span>
<span id="L125" rel="#L125">125</span>
<span id="L126" rel="#L126">126</span>
<span id="L127" rel="#L127">127</span>
<span id="L128" rel="#L128">128</span>
<span id="L129" rel="#L129">129</span>
<span id="L130" rel="#L130">130</span>

            </td>
            <td class="blob-line-code">
                    <div class="highlight"><pre><div class='line' id='LC1'><span class="cm">/**This is the sql script that sets up the schema for a semester&#39;s marks database**/</span></div><div class='line' id='LC2'><br/></div><div class='line' id='LC3'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">Staff</span></div><div class='line' id='LC4'>	<span class="p">(</span></div><div class='line' id='LC5'>		<span class="n">StaffID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC6'>		<span class="n">FirstName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">24</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC7'>		<span class="n">LastName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">24</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC8'><br/></div><div class='line' id='LC9'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">StaffID</span><span class="p">)</span></div><div class='line' id='LC10'>	<span class="p">);</span></div><div class='line' id='LC11'><br/></div><div class='line' id='LC12'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">Student</span></div><div class='line' id='LC13'>	<span class="p">(</span></div><div class='line' id='LC14'>		<span class="n">StudentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC15'>		<span class="n">FirstName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">30</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC16'>		<span class="n">LastName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">30</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC17'>		<span class="n">DissTitle</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">40</span><span class="p">),</span></div><div class='line' id='LC18'>		<span class="n">Discipline</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">5</span><span class="p">),</span></div><div class='line' id='LC19'>		<span class="n">Supervisor1</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC20'>		<span class="n">Supervisor2</span> <span class="nb">INT</span><span class="p">,</span></div><div class='line' id='LC21'>		<span class="n">Supervisor3</span> <span class="nb">INT</span><span class="p">,</span></div><div class='line' id='LC22'>		<span class="n">Supervisor4</span> <span class="nb">INT</span><span class="p">,</span></div><div class='line' id='LC23'><br/></div><div class='line' id='LC24'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">StudentID</span><span class="p">),</span></div><div class='line' id='LC25'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Supervisor1</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Staff</span> <span class="p">(</span><span class="n">StaffID</span><span class="p">),</span></div><div class='line' id='LC26'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Supervisor2</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Staff</span> <span class="p">(</span><span class="n">StaffID</span><span class="p">),</span></div><div class='line' id='LC27'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Supervisor3</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Staff</span> <span class="p">(</span><span class="n">StaffID</span><span class="p">),</span></div><div class='line' id='LC28'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Supervisor4</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Staff</span> <span class="p">(</span><span class="n">StaffID</span><span class="p">)</span></div><div class='line' id='LC29'>	<span class="p">);</span></div><div class='line' id='LC30'><br/></div><div class='line' id='LC31'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">Unit</span></div><div class='line' id='LC32'>	<span class="p">(</span></div><div class='line' id='LC33'>		<span class="n">UnitCode</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">8</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC34'>		<span class="n">UnitName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">30</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC35'>		<span class="n">Points</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC36'><br/></div><div class='line' id='LC37'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">)</span></div><div class='line' id='LC38'>	<span class="p">);</span></div><div class='line' id='LC39'><br/></div><div class='line' id='LC40'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">Discipline</span></div><div class='line' id='LC41'>	<span class="p">(</span></div><div class='line' id='LC42'>		<span class="n">DisciplineName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">5</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC43'>		<span class="n">Unit1Code</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">8</span><span class="p">),</span></div><div class='line' id='LC44'>		<span class="n">Unit2Code</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">8</span><span class="p">),</span></div><div class='line' id='LC45'>		<span class="n">Unit3Code</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">8</span><span class="p">),</span></div><div class='line' id='LC46'>		<span class="n">Unit4Code</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">8</span><span class="p">),</span></div><div class='line' id='LC47'><br/></div><div class='line' id='LC48'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">DisciplineName</span><span class="p">),</span></div><div class='line' id='LC49'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Unit1Code</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Unit</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">),</span></div><div class='line' id='LC50'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Unit2Code</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Unit</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">),</span></div><div class='line' id='LC51'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Unit3Code</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Unit</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">),</span></div><div class='line' id='LC52'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">Unit4Code</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Unit</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">)</span></div><div class='line' id='LC53'>	<span class="p">);</span></div><div class='line' id='LC54'><br/></div><div class='line' id='LC55'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">Assessment</span></div><div class='line' id='LC56'>	<span class="p">(</span></div><div class='line' id='LC57'>		<span class="n">AssessmentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC58'>		<span class="n">AssessmentName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">30</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC59'>		<span class="n">UnitCode</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">8</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC60'>		<span class="n">UnitPercent</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC61'><br/></div><div class='line' id='LC62'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">AssessmentID</span><span class="p">),</span></div><div class='line' id='LC63'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Unit</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">)</span></div><div class='line' id='LC64'>	<span class="p">);</span></div><div class='line' id='LC65'><br/></div><div class='line' id='LC66'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">SubAssessment</span></div><div class='line' id='LC67'>	<span class="p">(</span></div><div class='line' id='LC68'>		<span class="n">SubAssessmentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC69'>		<span class="n">SubAssessmentName</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">30</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC70'>		<span class="n">AssessmentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC71'>		<span class="n">AssessmentPercent</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC72'>		<span class="n">MaxMarks</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC73'><br/></div><div class='line' id='LC74'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">SubAssessmentID</span><span class="p">),</span></div><div class='line' id='LC75'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">AssessmentID</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Assessment</span> <span class="p">(</span><span class="n">AssessmentID</span><span class="p">)</span></div><div class='line' id='LC76'>	<span class="p">);</span></div><div class='line' id='LC77'><br/></div><div class='line' id='LC78'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">SubAssessmentMark</span></div><div class='line' id='LC79'>	<span class="p">(</span></div><div class='line' id='LC80'>		<span class="n">MarkID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC81'>		<span class="n">Mark</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC82'>		<span class="n">MarkerID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC83'>		<span class="n">StudentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC84'>		<span class="n">SubAssessmentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC85'>		<span class="n">InsideRange</span> <span class="nb">INT</span><span class="p">,</span></div><div class='line' id='LC86'><br/></div><div class='line' id='LC87'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">MarkID</span><span class="p">),</span></div><div class='line' id='LC88'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">MarkerID</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Staff</span> <span class="p">(</span><span class="n">StaffID</span><span class="p">),</span></div><div class='line' id='LC89'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">StudentID</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Student</span> <span class="p">(</span><span class="n">StudentID</span><span class="p">),</span></div><div class='line' id='LC90'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">SubAssessmentID</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">SubAssessment</span> <span class="p">(</span><span class="n">SubAssessmentID</span><span class="p">)</span></div><div class='line' id='LC91'>	<span class="p">);</span></div><div class='line' id='LC92'><br/></div><div class='line' id='LC93'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">AssessmentMark</span></div><div class='line' id='LC94'>	<span class="p">(</span></div><div class='line' id='LC95'>		<span class="n">MarkID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC96'>		<span class="n">Mark</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC97'>		<span class="n">StudentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC98'>		<span class="n">AssessmentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC99'><br/></div><div class='line' id='LC100'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">MarkID</span><span class="p">),</span></div><div class='line' id='LC101'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">StudentID</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Student</span> <span class="p">(</span><span class="n">StudentID</span><span class="p">),</span></div><div class='line' id='LC102'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">AssessmentID</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Assessment</span> <span class="p">(</span><span class="n">AssessmentID</span><span class="p">)</span></div><div class='line' id='LC103'>	<span class="p">);</span></div><div class='line' id='LC104'><br/></div><div class='line' id='LC105'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">UnitMark</span></div><div class='line' id='LC106'>	<span class="p">(</span></div><div class='line' id='LC107'>		<span class="n">MarkID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC108'>		<span class="n">Mark</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC109'>		<span class="n">Grade</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">2</span><span class="p">),</span></div><div class='line' id='LC110'>		<span class="n">StudentID</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC111'>		<span class="n">UnitCode</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">8</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC112'><br/></div><div class='line' id='LC113'>		<span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">MarkID</span><span class="p">),</span></div><div class='line' id='LC114'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">StudentID</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Student</span> <span class="p">(</span><span class="n">StudentID</span><span class="p">),</span></div><div class='line' id='LC115'>		<span class="k">FOREIGN</span> <span class="k">KEY</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">)</span> <span class="k">REFERENCES</span> <span class="n">Unit</span> <span class="p">(</span><span class="n">UnitCode</span><span class="p">)</span></div><div class='line' id='LC116'>	<span class="p">);</span></div><div class='line' id='LC117'><br/></div><div class='line' id='LC118'><span class="k">CREATE</span> <span class="k">TABLE</span> <span class="n">Information</span></div><div class='line' id='LC119'>	<span class="p">(</span>	</div><div class='line' id='LC120'>		<span class="k">Lock</span> <span class="nb">CHAR</span><span class="p">(</span><span class="mi">1</span><span class="p">)</span> <span class="k">NOT</span> <span class="k">NULL</span> <span class="k">DEFAULT</span> <span class="s1">&#39;X&#39;</span><span class="p">,</span></div><div class='line' id='LC121'>		<span class="n">Period</span> <span class="nb">INT</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC122'>		<span class="n">LastBackup</span> <span class="k">TIMESTAMP</span><span class="p">,</span></div><div class='line' id='LC123'>		<span class="n">AutoBackupSchedule</span> <span class="nb">CHAR</span> <span class="k">NOT</span> <span class="k">NULL</span><span class="p">,</span></div><div class='line' id='LC124'>		<span class="n">AutoBackupDue</span> <span class="k">TIMESTAMP</span><span class="p">,</span></div><div class='line' id='LC125'>		<span class="n">LastRestoreDate</span> <span class="k">TIMESTAMP</span><span class="p">,</span></div><div class='line' id='LC126'>		<span class="n">LastRestoreFrom</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">25</span><span class="p">),</span></div><div class='line' id='LC127'>		<span class="n">VersionSuperseded</span> <span class="nb">VARCHAR</span><span class="p">(</span><span class="mi">25</span><span class="p">),</span></div><div class='line' id='LC128'>		<span class="k">constraint</span> <span class="n">PK_T1</span> <span class="k">PRIMARY</span> <span class="k">KEY</span> <span class="p">(</span><span class="k">Lock</span><span class="p">),</span></div><div class='line' id='LC129'>		<span class="k">constraint</span> <span class="n">CK_T1_Locked</span> <span class="k">CHECK</span> <span class="p">(</span><span class="k">Lock</span><span class="o">=</span><span class="s1">&#39;X&#39;</span><span class="p">)</span></div><div class='line' id='LC130'>	<span class="p">);</span></div></pre></div>
            </td>
          </tr>
        </table>
  </div>

  </div>
</div>

<a href="#jump-to-line" rel="facebox[.linejump]" data-hotkey="l" class="js-jump-to-line" style="display:none">Jump to Line</a>
<div id="jump-to-line" style="display:none">
  <form accept-charset="UTF-8" class="js-jump-to-line-form">
    <input class="linejump-input js-jump-to-line-field" type="text" placeholder="Jump to line&hellip;" autofocus>
    <button type="submit" class="button">Go</button>
  </form>
</div>

        </div>

      </div><!-- /.repo-container -->
      <div class="modal-backdrop"></div>
    </div><!-- /.container -->
  </div><!-- /.site -->


    </div><!-- /.wrapper -->

      <div class="container">
  <div class="site-footer">
    <ul class="site-footer-links right">
      <li><a href="https://status.github.com/">Status</a></li>
      <li><a href="http://developer.github.com">API</a></li>
      <li><a href="http://training.github.com">Training</a></li>
      <li><a href="http://shop.github.com">Shop</a></li>
      <li><a href="/blog">Blog</a></li>
      <li><a href="/about">About</a></li>

    </ul>

    <a href="/">
      <span class="mega-octicon octicon-mark-github"></span>
    </a>

    <ul class="site-footer-links">
      <li>&copy; 2013 <span title="0.05336s from github-fe134-cp1-prd.iad.github.net">GitHub</span>, Inc.</li>
        <li><a href="/site/terms">Terms</a></li>
        <li><a href="/site/privacy">Privacy</a></li>
        <li><a href="/security">Security</a></li>
        <li><a href="/contact">Contact</a></li>
    </ul>
  </div><!-- /.site-footer -->
</div><!-- /.container -->


    <div class="fullscreen-overlay js-fullscreen-overlay" id="fullscreen_overlay">
  <div class="fullscreen-container js-fullscreen-container">
    <div class="textarea-wrap">
      <textarea name="fullscreen-contents" id="fullscreen-contents" class="js-fullscreen-contents" placeholder="" data-suggester="fullscreen_suggester"></textarea>
          <div class="suggester-container">
              <div class="suggester fullscreen-suggester js-navigation-container" id="fullscreen_suggester"
                 data-url="/jon2512chua/Honours-Marks-Database/suggestions/commit">
              </div>
          </div>
    </div>
  </div>
  <div class="fullscreen-sidebar">
    <a href="#" class="exit-fullscreen js-exit-fullscreen tooltipped leftwards" title="Exit Zen Mode">
      <span class="mega-octicon octicon-screen-normal"></span>
    </a>
    <a href="#" class="theme-switcher js-theme-switcher tooltipped leftwards"
      title="Switch themes">
      <span class="octicon octicon-color-mode"></span>
    </a>
  </div>
</div>



    <div id="ajax-error-message" class="flash flash-error">
      <span class="octicon octicon-alert"></span>
      <a href="#" class="octicon octicon-remove-close close ajax-error-dismiss"></a>
      Something went wrong with that request. Please try again.
    </div>

  </body>
</html>

