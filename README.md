# astah-miro Plugin
## About

The Astah-miro plugin enables you to import/export diagrams between Astah and miro.

Supported diagram types:
- Import from miro
    - UML Class diagram
    - Mind map
- Export to miro
    - UML Class diagram

*Note:* This plugin is an experimental version therefore no product support is provided. 

## How to install

1. Download the .jar file from [releases](https://github.com/ChangeVision/astah-miro-plugin/releases).
1. Start Astah.
1. Install the plugin at the Plugin List Dialog ([Plugin] -> [Installed Plugins] -> [Install])
1. Restart Astah.

## Setup

Create a configuration file `.astah-miro.json` under the home directory(`$HOME`) and edit the content below.

```json
{
  "token": "Access Token"
}
```

### How to get an Access Token
First of all, you need to have a miro account.

#### Step 1 - Get Developer Team

Click this link to create a development team.

https://miro.com/app/dashboard/?createDevTeam=1

#### Step 2 - Create an App

1. Open [this link](https://miro.com/app/settings/user-profile/apps)
1. Click `[Create new app]` button on the page.
1. Enter `App name` and `Description` in the dialog and click `[Create App]` to create an App.


#### Step 3 - Set the app permissions and get the token

1. Now you will be taken to the page where you configure datiles of the App. Under `OAuth scopes`, enable these two permissions (`boards:read` and `boards:write`).
1. Click `Install app to get OAuth token`.
1. Click `[Install]` to the designated team you want to install this app to.
    - Make sure you choose a team that has a board you want to connect with Astah instead of choosing `Dev team`.
1. An `Access Token` will appear, copy this access token and save it somewhere.
    - Do not expose this access token publicly.
1. Add this Access Token to the `"token"` of `.astah-miro.json` file.

Reference: https://developers.miro.com/docs/getting-started#section-step-4-install-app-to-test-rest-api-or-web-plugin


## Usage

### How to import UML Class diagram from miro

1. Launch Astah and create an Class diagram (`[Diagram] -> [Class Diagram]`)
1. Go to `[Tools] -> [miro] -> [Import diagrams from miro]`


### How to import Mindmap diagram from miro

1. Launch Astah and create a Mindmap (`[Diagram] -> [Mindmap]`)
1. Go to `[Tools] -> [miro] -> [Import diagrams from miro]`

*Note:* Cannot import if miro board has more than one mind map


### How to export UML Class diagram to miro

1. Launch Astah and open a UML Class diagram you want to export to miro
1. Go to `[Tools] -> [miro] -> [Export diagram to miro]`


## License

Copyright 2020 Change Vision, Inc.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
