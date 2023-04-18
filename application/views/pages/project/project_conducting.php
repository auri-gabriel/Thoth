<script src="<?= base_url('assets/js/bibupload.js'); ?>"></script>
<style>
td.file-name {
  max-width: 150px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
<div class="card">
	<div class="text-center card-header">
		<h4><?= $project->get_title(); ?></h4>
		<input type="hidden" id="id_project" value="<?= $project->get_id(); ?>">
		<a href="<?= base_url('open/' . $project->get_id()) ?>"
		   class="btn form-inline btn-outline-primary opt">Overview <i class="fas fa-binoculars"></i></a>
		<a href="<?= base_url('planning/' . $project->get_id()) ?>"
		   class="btn form-inline btn-outline-primary opt">Planning <i class="fas fa-list"></i></a>
		<?php if ($this->session->level == "4") { ?>
			<a href="<?= base_url('study_selection_adm/' . $project->get_id()) ?>"
			   class="btn form-inline btn-primary opt">Conducting <i class="fas fa-play-circle"></i></a>
		<?php } else { ?>
			<a href="<?= base_url('conducting/' . $project->get_id()) ?>"
			   class="btn form-inline btn-primary opt">Conducting <i class="fas fa-play-circle"></i></a>
		<?php } ?>
		<a href="<?= base_url('reporting/' . $project->get_id()) ?>"
		   class="btn form-inline btn-outline-primary opt">Reporting <i class="fas fa-chart-line"></i></a>
		<?php
		if ($project->get_planning() == 100) {
			?>
			<a href="<?= base_url('export/' . $project->get_id()) ?>"
			   class="btn form-inline btn-outline-primary opt">Export <i class="fas fa-file-download"></i></a>
			<?php
		}
		?>
	</div>
	<div class="card-body">
		<h4>Conducting</h4>
		<?php
		if ($project->get_planning() == 100) {
			?>
			<ul class="nav nav-pills nav-justified flex-column flex-sm-row">
				<?php if ($this->session->level != "4") { ?>
					<li class="nav-item">
						<a class="nav-link active flex-sm-fill text-sm-center"
						   href="<?= base_url('conducting/' . $project->get_id()) ?>">Import Studies <i class="fas fa-upload"></i></a>
					</li>
					<li class="nav-item">
						<a class="nav-link flex-sm-fill text-sm-center" href="<?= base_url('study_selection/' . $project->get_id()) ?>">Study
							Selection <i class="fas fa-clipboard-check"></i></a>
					</li>
				<?php } ?>
				<?php if ($this->session->level == "4") { ?>
					<li class="nav-item">
						<a class="nav-link flex-sm-fill text-sm-center" href="<?= base_url('study_selection_adm/' . $project->get_id()) ?>">Review
							Study
							Selection <i class="fas fa-book-reader"></i></a>
					</li>
				<?php } ?>
				<?php if ($this->session->level != "4") { ?>
					<li class="nav-item">
						<a class="nav-link flex-sm-fill text-sm-center"
						   href="<?= base_url('quality_assessment/' . $project->get_id()) ?>">Quality
							Assessment <i class="fas fa-star-half-alt"></i></a>
					</li>
				<?php } ?>
				<?php if ($this->session->level == "4") { ?>
					<li class="nav-item">
						<a class="nav-link flex-sm-fill text-sm-center" href="<?= base_url('quality_adm/' . $project->get_id()) ?>">Review
							Quality Assessment <i class="fas fa-book-reader"></i></a>
					</li>
				<?php } ?>
				<?php if ($this->session->level != "4") { ?>
					<li class="nav-item">
						<a class=" nav-link flex-sm-fill text-sm-center" href="<?= base_url('data_extraction/' . $project->get_id()) ?>">Data
							Extraction <i class="fas fa-table"></i></a>
					</li>
				<?php } ?>
			</ul>
			<br>
			<div class="form-inline">
				<label for="database_import"><strong>Import Studies</strong></label>
				<a class="float-right opt"><i class="fas fa-question-circle "></i></a>
			</div>
			<div class="form-inline">
				<div class="input-group col-md-3">
					<label for="database_import" class="col-sm-12">Database</label>
					<select class="form-control" id="database_import">
						<?php foreach ($project->get_databases() as $database) { ?>
							<option><?= $database->get_name() ?></option>
						<?php } ?>
					</select>
				</div>
				<div class="input-group col-md-6">
					<label for="upload_bib" class="col-sm-12">Choose file</label>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="upload_bib" accept=".bib,.csv"
							   onchange="change_name()">
						<label class="custom-file-label" id="name_bib_upload" for="upload_bib"></label>
					</div>
					<div class="input-group-append">
						<button class="btn btn-success" type="button" onclick="readFileAsString();"><span
								class="fas fa-plus"></span>
						</button>
					</div>
				</div>
			</div>
			<br>
			<table id="table_imported_studies" class="table table-responsive-sm">
				<caption>List of Imports of Data Tables</caption>
				<thead>
				<tr>
					<th>Database</th>
					<th>Imported Studies</th>
					<th>Files Imported</th>
				</tr>
				</thead>
				<tbody>
				<?php foreach ($project->get_databases() as $database) { ?>
					<tr>
						<td><?= $database->get_name() ?></td>
						<td><?= $num_papers[$database->get_name()] ?></td>
						<td>
							<table id="table_<?= $database->get_name() ?>" class="table">
								<th>File</th>
								<th>Delete</th>
								<tbody>
								<?php foreach ($bib[$database->get_name()] as $b) { ?>
									<tr>
										<td class="file-name"><?= $b ?></td>
										<td>
											<button class="btn btn-danger" onClick="delete_bib(this)">
												<span class="far fa-trash-alt"></span>
											</button>
										</td>
									</tr>
								<?php } ?>
								</tbody>
							</table>
						</td>
					</tr>
				<?php } ?>
				</tbody>
			</table>
			<?php
		} else {
			?>
			<div class="alert alert-warning container-fluid alert-dismissible fade show" role="alert">
				<h5>Complete these tasks to advance</h5>
				<ul>
					<?php
					foreach ($project->get_errors() as $error) {
						?>
						<li><?= $error ?></li>
						<?php
					}
					?>
				</ul>
			</div>
			<?php
		}
		?>
	</div>
</div>
