# astah-miro Plugin
## About

astah*とmiro間で図のインポート/エクスポートを可能にするプラグインです。
対応している図は以下です。

- miroからのインポート
    - クラス図
    - マインドマップ
- miroへのエクスポート
    - クラス図

*注意:* 本プラグインは実験的な実装につきサポート対象外となります。

## How to install

1. [release](https://github.com/ChangeVision/astah-miro-plugin/releases)から.jarファイルをダウンロードする
1. astahを起動する
1. プラグイン一覧ダイアログからプラグインをインストールする([プラグイン] -> [インストール済みプラグイン] -> [インストール])
1. astahを再起動する

## Setup

設定ファイルとして、ホームディレクトリ(`$HOME`)直下に`.astah-miro.json`を作成し、以下の内容を記述します。

```json
{
  "token": "Access Token"
}
```

### Access Tokenの取得方法
miroのアカウントは作成済とします。

#### Step 1 - Developer Teamを作成する

以下のリンクから開発用Teamを作成します。

https://miro.com/app/dashboard/?createDevTeam=1

#### Step 2 - Appを作成する

1. [このリンク](https://miro.com/app/settings/user-profile/apps)を開く
1. 開かれたページの`[Create new app]`をクリック
1. ダイアログに適当な`App name`と`Description`を入力し`[Create App]`をクリック


#### Step 3 - アプリの権限を設定しトークンを取得する

1. Step2の後に遷移したページの中段にある`OAuth scopes`から必要な権限にチェックを入れる（`boards:read`と`boards:write`）
1. `Install app to get OAuth token`をクリック
1. ダイアログから適当なTeamの`[Install]`をクリック
    - ここで選択するTeamは`Dev team`ではなく、実際にastah*と連携させたいボードがあるTeamとなります）
1. 表示された`Access Token`をひかえる
    - ここで表示されたtokenは流出させないようにしてください
1. 取得したAccess Tokenを`.astah-miro.json`の`"token"`に記述する

see also: https://developers.miro.com/docs/getting-started#section-step-4-install-app-to-test-rest-api-or-web-plugin

## Usage

### インポート

1. miroのボードからインポートしたい図の空の図をastah*で開く（`[図] -> [クラス図] or [マインドマップ]`）
1. `[ツール] -> [miro] -> [miroから図をインポートする]`

※図を開いた状態でないとインポートできません

※SysMLはマインドマップのみの対応です

### エクスポート

1. miroへエクスポートしたい図をastah*で開く
1. `[ツール] -> [miro] -> [図をmiroへエクスポートする]`


## License

Copyright 2020 Change Vision, Inc.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
